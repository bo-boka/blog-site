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

        $.ajax({
            url: 'page',
            type: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            data: JSON.stringify({
                title: $('#addPageTitle').val(),
                content: contentData.getContent()
                        // hardcoded sample data
//                image: "example.com/image.png"
            })
        }).success(function (data, status) {
            $('#addPageTitle').val('');
            $('#addPageContent').val('');

            loadPages();
        });
    });
    // read

    // update 

    // delete

    // table
});


function loadPages() {
    $.ajax({
        //describe the request!
        url: 'allpages',
        type: 'GET'
    }).success(function (data) {
        fillPageTable(data);
        fillCausesDropdown(data);
    });
}

function fillPageTable(pageList, status) {
    $('#pageRows').empty();

    $.each(pageList, function (index, page) {
        $('#pageRows').append($('<tr>')
                .append($('<td>').text(page.author))
                .append($('<td>').text(page.title))
                .append($('<td>').append($('<a>').attr({
                    'data-page-id': page.id,
                    'data-toggle': 'modal',
                    'data-target': '#editModal'
                })
                        .text('Edit')))
                .append($('<td>').append($('<a>').attr({
                    'onClick': 'deletePage(' + page.id + ')'
                })
                        .text('Delete')))
                .append($('<td>').text(page.date))
                );
    });
}

function fillCausesDropdown(causeList, status) {
    $('#causesDD').empty();

    $.each(causeList, function (index, page) {
        $('#causesDD').append($('<li>').text(page.title));
//                .append($('<a>').attr({
//                    'onchange': 'displayCausePage(' + page.id + ')'
//                }).text(page.title))
//                );
    });
}