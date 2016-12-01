/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function () {
    
    loadBlogs();
    
    // create
    $('#addButton').click( function (event) {
        event.preventDefault();
        
        var contentData = tinyMCE.get('addBlogContent');
        
        
        $.ajax({
            url: 'blog',
            type: 'POST',
            data: JSON.stringify({
                title: $('#addTitle').val(),
                content: contentData.getContent(),
                
                tags: ["tag1", "tag2"], 
                category: "fdjsiofd",
                author: "admin",
                date: "01/01/1999",
//                image: "example.com/image.png",
                published: false
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success( function (data, status) {
            $('#addTitle').val('');
            $('#addCategory').val('');
            $('#addTags').val('');
            tinyMCE.activeEditor.setContent('');
            
            loadBlogs();
        });
    });
    // read

    // update 

    // delete

    // table


});

function loadBlogs() {
    
    $.ajax({
        //describe the request!
        url: 'blogs',
        type: 'GET'
    }).success(function(data){
        fillDraftBlogTable(data);
    });
    
}

function fillDraftBlogTable(blogList, status) {
    $('#draftRows').empty();
    
    $.each(blogList, function (index, blog) {
       $('#draftRows').append($('<tr>')
                        .append($('<td>').text(blog.author))
                        .append($('<td>').text(blog.title))
                        .append($('<td>').append($('<a>').attr({
                                                            'data-blog-id': blog.id,
                                                            'data-toggle': 'modal',
                                                            'data-target': '#editModal'
                                                            })
                                                            .text('Edit')))
                        .append($('<td>').append($('<a>').attr({
                                                            'onClick': 'deleteBlog(' + blog.id + ')'
                                                            })
                                                            .text('Delete')))                           
                        .append($('<td>').text(blog.date))
                
                
                ); 
    });


}

function fillPublishedBlogTable(blogList, status) {
    $('#publishedRows').empty();
    
    $.each(blogList, function (index, blog) {
        $('#publishedRows').append($('<tr>')
                            .append($('<td>').text(blog.author))
                            .append($('<td>').text(blog.title))
                            .append($('<td>').append($('<a>').attr({
                                                                'data-blog-id': blog.id,
                                                                'data-toggle': 'modal',
                                                                'data-target': '#editModal'
                                                                })
                                                                .text('Edit')))
                            .append($('<td>').append($('<a>').attr({
                                                                'onClick': 'deleteBlog(' + blog.id + ')'
                                                                })
                                                                 .text('Delete')))
                            .append($('<td>').text(blog.date))
                            
                );
    });
}
