//load home page functionality
$(document).ready(function(){
loadPosts();

$("#searchCategory").change(function(event){
    var searchCategory = $("#searchCategory").val();
    event.preventDefault();
    getCategory(searchCategory);
    });
});

//process the posts

function processBlogList(data){
    var tableRows = $("#HomeRows");
    
    $.each(data, function (index, Blog){
        var titleField = $("<td>");
        var dateField = $("<td>");
        var authorField = $("<td>");
        var categoryField = $("<td>");
        var tagsField = $("<td>");
        var contentField = $("<td>");
        
        titleField.text(Blog.title);
        dateField.text(Blog.date);
        authorField.text("By: "+Blog.author);
        categoryField.text("In category: "+Blog.category);
        tagsField.text(Blog.tags);
        contentField.text(Blog.content);
        
        var firstRow = $('<tr style="background-color: lightblue">');
        firstRow.append(titleField);
        firstRow.append(dateField);
        
        var secondRow = $("<tr>");
        secondRow.append(contentField);
        secondRow.append("</br>");
        
        var thirdRow = $('<tr>');
        thirdRow.append(tagsField);
        thirdRow.append(categoryField);
        thirdRow.append(authorField);
        
        tableRows.append(firstRow);
        tableRows.append(secondRow);
        tableRows.append(thirdRow);
    });
}

function loadPosts(){
    
    $.ajax({
        url: "blogs",
        type: "GET"
    }).success(function (data){
        processBlogList(data);
    });
}

function getCategory(category){
    
    $.ajax({
        url: "blogListDisplayCategory/"+category,
        type: "GET",
        headers: {
            "Accept": "application/json"
        }
    }).success(function (data){
        processBlogList(data);
    });
}
