/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    
    loadPages();

    $('#addPageButton').click(function (event) {
        event.preventDefault();

        var contentData = tinyMCE.get('addPageContent');
        var pageTitle = $('#addPageTitle').val();
        var pageContent = contentData.getContent();

        $.ajax({
            url: 'page',
            type: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            data: JSON.stringify({
                title: pageTitle,
                content: pageContent
            })
        }).success(function (data, status) {
            $('#addPageTitle').val('');
            contentData.setContent('');
            window.location="http://localhost:8080/BlogCapstone/admin";
            loadPages();
            window.onbeforeunload = function() {};
        }).error(function (data, status) {
            var errorDiv = $("#validationPageErrors");
            errorDiv.empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
                errorDiv.show();
            });
        });
    });



    $('#editPageModal').on("show.bs.modal", function (event) {
        var element = $(event.relatedTarget);
        var id = element.data("page-id");
        editPage(id);
    });

    $('#editPageButton').click(function (event) {
        var contentData = tinyMCE.get('page-edit-content');
        var pageTitle = $("#page-edit-title").val();
        var pageContent = contentData.getContent();
        event.preventDefault();
        updatePage();
    });
});
// fill modal
function editPage(id) {

//    var x = tinyMCE.get('page-edit-content');
//    var pageContent = contentData.getContent();

    $.ajax({
        url: "page/" + id,
        type: "GET",
        headers: {
            "Accept": "application/json"
        }
    }).success(function (page) {
        var test = page.content;
        $("#page-edit-id").val(page.id);
        $("#page-edit-title").val(page.title);
        $("#page-edit-content").val(tinyMCE.get('page-edit-content').setContent(test));
    });
}

function loadPages() {
    $.ajax({
        //describe the request!
        url: 'allpages',
        type: 'GET'
    }).success(function (data) {
        fillPageTable(data);
    });
}

function fillPageTable(pageList, status) {
    $('#pageRows').empty();

    $.each(pageList, function (index, page) {
        $('#pageRows').append($('<tr>')
                .append($('<td>').text(page.title))
                .append($('<td>')
                        .append($('<button>').attr({
                            'class': 'btn btn-primary',
                            'data-toggle': 'modal',
                            'data-target': '#editPageModal',
                            'data-page-id': page.id
                        }).text('Edit')))

                .append($('<td>').append($('<button>').attr({
                    'class': 'btn btn-danger',
                    'onClick': 'deletePage(' + page.id + ')'})
                        .text('Delete')))
                );
    });
}

// delete

function deletePage(id) {
    $.ajax({
        type: "DELETE",
        url: "page/" + id
    }).success(function () {
        loadPages();
    });
}

//edit page

function updatePage() {

    var contentData = tinyMCE.get('page-edit-content');
    var pageId = $("#page-edit-id").val();
    var pageTitle = $("#page-edit-title").val();
    var pageContent = contentData.getContent();
    var pageImage = null;

    $.ajax({
        url: "page/" + pageId,
        type: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        "dataType": "json",
        data: JSON.stringify({
            title: pageTitle,
            content: pageContent,
            image: null,
            id: pageId
        })

    }).success(function (data) {
        loadPages();
        $("#editPageModal").modal('hide');
        $("#validationPageEditErrors").empty();
    }).error(function (data, status) {
        var errorDiv = $("#validationPageEditErrors");
        errorDiv.empty();
        errorDiv.show();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            errorDiv.append(validationError.message);
            errorDiv.append("<br>");
            
//            $("#editPageModal").on('hide', function(e){
//                e.preventDefault();
//            });
        });
    });

}
