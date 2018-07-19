<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/DataTables-1.10.16/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="../static/Buttons-1.5.1/css/buttons.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="../static/air-datepicker/css/datepicker.min.css"/>

    <script src="../static/jquery/jquery-3.3.1.min.js"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../static/DataTables-1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="../static/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript" src="../static/Buttons-1.5.1/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="../static/Buttons-1.5.1/js/buttons.bootstrap4.min.js"></script>
    <script type="text/javascript" src="../static/Buttons-1.5.1/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="../static/air-datepicker/js/datepicker.min.js"></script>
    <script type="text/javascript" src="../static/air-datepicker/js/i18n/datepicker.zh-TW.js" charset="UTF-8"></script>


</head>

<body>
<section id="searchBlock">
    <div class="container-fluid">
        <div class="form-group row">
            <label class="col-2 col-form-label">查詢日期：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="startDate" placeholder="開始">
            </div>
            <div class="col-5">
                <input type="text" class="form-control" id="endDate" placeholder="結束">
            </div>
        </div>
        <div class="text-right">
            <button type="button" class="btn btn-outline-primary" onclick="search()">查詢</button>
        </div>

        <hr>
        <div class="form-group row">
            <label class="col-2 col-form-label">名稱：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="payName" placeholder="開始">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-2 col-form-label">金額：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="payValue" placeholder="開始">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">初分類：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="firstClassify" placeholder="開始">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">細分類：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="secondClassify" placeholder="開始">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">付錢人：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="userId" placeholder="開始">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-2 col-form-label">時間：</label>
            <div class="col-5">
                <input type="text" class="form-control" id="createTime" placeholder="開始">
            </div>
        </div>
        <div class="text-right">
            <button type="button" class="btn btn-outline-primary" onclick="add()">新增</button>
        </div>
        <hr>
        <div class="form-group row">
            <label class="col-2 col-form-label">序號</label>
            <div class="col-5">
                <input type="text" class="form-control" id="serial" placeholder="開始">
            </div>
        </div>
        <div class="text-right">
            <button type="button" class="btn btn-outline-primary" onclick="deleteRow()">刪除</button>
        </div>


        <hr>
        <table id="showTable" class="table table-striped table-bordered table-hover responsive mt-2"
               style="width: 100%">
        </table>
    </div>
</section>
<script>
    var table;
    //RESTful api 位置
    var apiUrl = '';
    $(document).ready(function () {
        $('#headerTitle').text('30日自動結案報表查詢');
        $('#startDate,#endDate').datepicker({
            language: 'zh-TW'
        });

        var d = new Date();
        $('#startDate').datepicker().data('datepicker').selectDate(new Date(d.getFullYear(), d.getMonth(), '1'));
        $('#endDate').datepicker().data('datepicker').selectDate(d);
        initDataTable();
    });

    function initDataTable() {
        table = $('#showTable').DataTable({
            language: {
                url: '../static/DataTables-1.10.16/zh-tw.json'
            },
            ajax: {
                url: apiUrl,
                method: 'get',
                data: getSearchData
            },
            columns: [{
                data: 'Serial',
                title: '序號'
            }, {
                data: 'CaseNo',
                title: '申請案件編號'
            }, {
                data: 'UTime',
                title: '進件日期'
            }, {
                data: 'CaseNoWeb',
                title: 'APS案件編號'
            }, {
                data: 'GMCardNo',
                title: '現金卡系統案件編號'
            }, {
                data: 'Status',
                title: '狀態'
            }, {
                data: 'EndDate',
                title: '結案日期'
            }],
            scrollX: true,
            scrollCollapse: true,
            dom: 'Bfrtip',
            buttons: [{
                extend: 'excel',
                title: '30日自動結案報表查詢',
                className: 'btn-outline-secondary'
            }]
        });
    }


    function getSearchData(d) {
        return $.extend({}, d, {
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
        })
    }

    function search() {
        if (table) {
            table.ajax.reload();
        } else {
            initDataTable();
        }
    }
</script>
</body>

</html>