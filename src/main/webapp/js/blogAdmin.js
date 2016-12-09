/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    
    loadBlogs();

    $('#blogExpand').click(function () {
        $(".read-more-target").toggle();
        $(this).text(function (i, v) {
            return v === 'Show All' ? 'Show Less' : 'Show All';
        });
    });

    $('#blogPublishedExpand').click(function () {
        $(".read-more-target-published").toggle();
        $(this).text(function (i, v) {
            return v === 'Show All' ? 'Show Less' : 'Show All';
        });
    });

    $('#editBlogModal').on("show.bs.modal", function (event) {
        var element = $(event.relatedTarget);
        var id = element.data("blog-id");
        editBlog(id);
    });

    // create .addButton
    $('#publishBlog').click(function (event) {
        event.preventDefault();
        var contentData = tinyMCE.get('addBlogContent');
        var tagArray = $('#addTags').val().split(" ");
        var blogCategory = $('#addCategory').val() === "addNewCategory" ?
                $('#newCategory').val() : $('#addCategory').val();
        var isPublished = this.id === 'publishBlog';
        $.ajax({
            url: 'blog',
            type: 'POST',
            data: JSON.stringify({
                title: $('#addTitle').val(),
                content: contentData.getContent(),
                tags: tagArray,
                category: blogCategory,
                author: "admin",
                date: $('#datePicker').val(),
                published: isPublished
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $("#validationErrors").hide();
            loadBlogs();
            window.onbeforeunload = function() {};

            $('#addTitle').val('');
            $('#addCategory').val('');
            $('#addTags').val('');
            $('#datePicker').val('');
            contentData.setContent('');
            $('#newCategory').fadeOut();
        }).error(function (data, status) {
            var errorDiv = $("#validationErrors");
            errorDiv.empty();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
                errorDiv.show();
            });
        });
    });

    $('#draftBlog').click(function (event) {
        event.preventDefault();
        var contentData = tinyMCE.get('addBlogContent');
        var tagArray = $('#addTags').val().split(" ");
        var blogCategory = $('#addCategory').val() === "addNewCategory" ?
                $('#newCategory').val() : $('#addCategory').val();
        var isPublished = this.id === 'publishBlog';
        $.ajax({
            url: 'blog',
            type: 'POST',
            data: JSON.stringify({
                title: $('#addTitle').val(),
                content: contentData.getContent(),
                tags: tagArray,
                category: blogCategory,
                author: "admin",
                date: $('#datePicker').val(),
                published: isPublished
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            var errorDiv = $("#validationErrors");
            errorDiv.empty();
            $("#validationErrors").hide();
            loadBlogs();
            window.onbeforeunload = function() {};

            $('#addTitle').val('');
            $('#addCategory').val('');
            $('#addTags').val('');
            $('#datePicker').val('');
            contentData.setContent('');
            $('#newCategory').fadeOut();

        }).error(function (data, status) {
            var errorDiv = $("#validationErrors");
            errorDiv.empty();
            errorDiv.show();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append("<br>");
            });
        });
    });

    $('#addCategory').change(function () {
        if ($(this).val() === "addNewCategory") {
            $('#newCategory').fadeIn();
        } else {
            $('#newCategory').fadeOut();
        }
    });

    $('#datePicker').datepicker({
        showAnim: "slide",
        dateFormat: 'yy-mm-dd'
    });
    
    $('#editDatePicker').datepicker({
        showAnim: "slide",
        dateFormat: 'yy-mm-dd'
    });

//    $('#editDatePicker').datepicker({
//        showAnim: "slide"
//    });

    $("#editPublishBlog").click(function (event) {
        event.preventDefault();
        var published = true;
        updateBlogEdit(published);
    });

    $("#editDraftBlog").click(function (event) {
        event.preventDefault();
        var published = false;
        updateBlogEdit(published);
    });
});

function loadBlogs() {

    $.ajax({
        //describe the request!
        url: 'blogs',
        type: 'GET'
    }).success(function (data) {
        fillDraftBlogTable(data);
        loadCategories();
    });

}

function fillDraftBlogTable(blogList, status) {
    $('#draftRows').empty();
    $('#publishedRows').empty();

    var draftCounter = 0;
    var publishedCounter = 0;

    $.each(blogList, function (index, blog) {
        console.log("index is: " + index + ", blog id is: " + blog.id);

        if (blog.published === false) {
            $('#draftRows').append($('<tr>').attr('class', draftCounter > 4 ? 'read-more-target' : '')
                    .append($('<td>').text(blog.date))
                    .append($('<td>').text(blog.author))
                    .append($('<td>').text(blog.title))
                    .append($('<td>').append($('<button>').attr({
                        'class': 'btn btn-primary',
                        'data-toggle': 'modal',
                        'data-target': '#editBlogModal',
                        'data-blog-id': blog.id
                    })
                            .text('Edit')))
                    .append($('<td>').append($('<button>').attr({
                        'class': 'btn btn-danger',
                        'onClick': 'deleteBlog(' + blog.id + ')'
                    })
                            .text('Delete')))
                    .append($('<td>').append($('<button>').attr({
                        'onClick': 'publishBlog(' + blog.id + ')',
                        'class': 'btn btn-default'
                    })
                            .text('Publish')))
                    );
            draftCounter++;
            console.log("draftCounter: " + draftCounter);
        } else {
            $('#publishedRows').append($('<tr>').attr('class', publishedCounter > 4 ? 'read-more-target-published' : '')
                    .append($('<td>').text(blog.date))
                    .append($('<td>').text(blog.author))
                    .append($('<td>').text(blog.title))
                    .append($('<td>').append($('<button>').attr({
                        'class': 'btn btn-primary',
                        'data-toggle': 'modal',
                        'data-target': '#editBlogModal',
                        'data-blog-id': blog.id
                    })
                            .text('Edit')))
                    .append($('<td>').append($('<button>').attr({
                        'class': 'btn btn-danger',
                        'onClick': 'deleteBlog(' + blog.id + ')'
                    })
                            .text('Delete')))
                    .append($('<td>').append($('<button>').attr({
                        'onClick': 'publishBlog(' + blog.id + ')',
                        'class': 'btn btn-default'
                    })
                            .text('Unpublish')))



                    );
            publishedCounter++;
        }

    });

}

function fillPublishedBlogTable(blogList, status) {
    $('#publishedRows').empty();

    $.each(blogList, function (index, blog) {
        $('#publishedRows').append($('<tr>').attr('class', index > 4 ? 'read-more-target-published' : '')
                .append($('<td>').text(blog.author))
                .append($('<td>').text(blog.title))
                .append($('<td>').append($('<a>').attr({
                    'data-toggle': 'modal',
                    'data-target': '#editBlogModal',
                    'data-blog-id': blog.id
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

function deleteBlog(id) {

    $.ajax({
        type: 'DELETE',
        url: 'blog/' + id
    }).success(function () {
        loadBlogs();
    });
}

function publishBlog(id) {

    $.ajax({
        type: 'GET',
        url: 'blog/' + id
    }).success(function (blog) {
        blog.published = !blog.published;
        updateBlog(blog);
    });
}

function editBlog(id) {

    $.ajax({
        url: "blog/" + id,
        type: "GET",
        headers: {
            "Accept": "application/json"
        }
    }).success(function (blog) {
        var innerContent = blog.content;
        var categoryToEdit = blog.category;
        var titleToEdit = blog.title;
        var idToEdit = blog.id;
        var dateToEdit = blog.date;
        var tagsToEdit = blog.tags;

        $("#editBlogDate").val(dateToEdit);
        var tags = "";
        if (tags !== null) {
            for (var i = 0; i < tagsToEdit.length; i++) {
                tags += tagsToEdit[i] + " ";
            }
        }
        $("#editTags").val(tags);
        $("#editId").val(idToEdit);
        $("#editCategory").val(categoryToEdit);
        $("#editTitle").val(titleToEdit);
        $("#editDatePicker").val(dateToEdit);
        $("#editBlogContent").val(tinyMCE.get('editBlogContent').setContent(innerContent));
    });
}

function updateBlogEdit(published) {

    var contentData = tinyMCE.get('editBlogContent');
    var blogTitle = $("#editTitle").val();
    var blogContent = contentData.getContent();
    var blogTags = $("#editTags").val().split(" ");
    var blogCategory = $("#editCategory").val();
    var blogDate = $("#editDatePicker").val();
    var blogId = $("#editId").val();

    published;

    $.ajax({
        url: 'blog/' + blogId,
        type: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        data: JSON.stringify({
            title: blogTitle,
            content: blogContent,
            author: "admin",
            date: blogDate,
            category: blogCategory,
            published: published,
            id: blogId,
            tags: blogTags
        })
    }).success(function (data, status) {
        loadBlogs();
        $("#editBlogModal").modal('hide');
        $("#validationBlogEditErrors").empty();
    }).error(function (data, status) {
        var errorDiv = $("#validationBlogEditErrors");
        errorDiv.empty();
        errorDiv.show();
        $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            errorDiv.append(validationError.message);
            errorDiv.append("<br>");
        });
    });
}

function updateBlog(blog) {

    $.ajax({
        type: 'PUT',
        url: 'blog/' + blog.id,
        data: JSON.stringify({
            title: blog.title,
            content: blog.content,
            tags: blog.tags,
            category: blog.category,
            author: blog.author,
            date: blog.date,
            published: blog.published,
            id: blog.id,
            image: null
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        loadBlogs();
    });
}

function loadCategories() {
    $('#addCategory').empty();

    $.ajax({
        url: 'categories',
        type: 'GET'
    }).success(function (data) {
        $.each(data, function (index, category) {
            $('#addCategory').append($('<option>').text(category));
        });
        $('#addCategory').append($('<option>').attr({
            value: 'addNewCategory'
        }).text('Add New Category...'));
    });
}


