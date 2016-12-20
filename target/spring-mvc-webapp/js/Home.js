//load home page functionality
$(document).ready(function () {
    loadPosts();
});


function clearTable() {
    $("#HomeRows").empty();
}

//process the posts

function processBlogList(data) {

    clearTable();

    $('#blogHomeExpand').click(function () {
        $(".read-more-target").toggle();
        $(this).text(function (i, v) {
            return v === 'Show All' ? 'Show Less' : 'Show All';
        });
    });

    var count = 0;
    
    $.each(data, function (index, blog) {

        var rows = $("#HomeRows");
        var nextRows = $("#nextHomeRows").attr('class', index => 5 ? 'read-more-target' : '');
        var first = $("#firstBlog");
        
        var inputDate = new Date(blog.date);
            inputDate = new Date(inputDate.getUTCFullYear(), inputDate.getUTCMonth(), inputDate.getUTCDate());
        var todaysDate = new Date();

        if (blog.published === true && inputDate < todaysDate) {
            
            if (count === 0) {
                first.append($("<div>").text(blog.date));
                first.append($("<h2>").append($("<a>").attr({
                    'onclick' : 'goToBlog(' +blog.id+ ')'
                }).text(blog.title)));
                first.append($("<div> <b>").text("Category: "+blog.category));
                first.append($("<div>").append(blog.content));
                first.append($("<div>").text("Posted by: "+blog.author));
                first.append($("<div align='right'>").text(blog.tags));

                count++;
            } else if (0 < count) {
                var titleField = $("<h4>");
                var dateField = $("<div>");
                var authorField = $("<div>");
                var categoryField = $("<div>");
                var contentField = $("<div>");
                var tagsField = $("<div align='right'>");

                titleField.append($("<h4>").append($("<a>").attr({
                    'onclick' : 'goToBlog(' +blog.id+ ')'
                }).text(blog.title)));
                dateField.text(blog.date);
                authorField.text("Posted by: "+blog.author);
                categoryField.text("Category: "+blog.category);
                contentField.append(blog.content);
                tagsField.text(blog.tags);

                var row = $('<div class="row-buffer">');

                row.append(dateField);
                row.append(titleField);
                row.append(categoryField);
                row.append(contentField);
                row.append(authorField);
                row.append(tagsField);

                if (count > 0 && count < 5) {
                    rows.append(row);
                    count++;
                } else if (count => 5) {
                    nextRows.append(row);
                    count++;
                }
            }

        }
    });
}

function loadPosts() {

    $.ajax({
        url: "blogs",
        type: "GET"
    }).success(function (data) {
        processBlogList(data);
    });
}

function goToBlog(blogID){
    $.ajax({
        url: "singleBlog/" + blogID,
        type: "GET",
        headers: {
            'Accept' : 'application/json'
        }
    }).success(function(blog){
        window.location="http://localhost:8080/BlogCapstone/singleBlog/"+ blogID;
    });
}
