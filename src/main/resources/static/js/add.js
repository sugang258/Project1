console.log("write");
let count = 1;
$("#fileAdd").click(function(){

    if(count<6) {
        let r = '<div class="mb-3">';
        r = r+'<label for="contents" class="form-label">File</label>';
        r = r+'<input type="file" name="files">';
        r = r+'<button type="button" class="del">X</button>';
        r = r+'</div>';
        $("#files").append(r);
        count++;
    }else{
        alert("최대 5개만 가능");
    }

})

$("#files").on("click",".del",function(){
    $(this).parent().remove();
    count--;
})

// let results=[false,true];

// $("#title").blur(function(){
//     console.log("title");
//     let title = $("#title").val();
//     let result = nullCheck(title, $("#inputTitleResult"), "TITLE");
//     results[0] = result;
//     console.log(result);
// })

// $("#contents").blur(function(){
//     let contents = $("#contents").val();
//     let result = nullCheck(contents, $("#inputContentsResult"),"CONTENTS");
//     results[1] = result;
//     console.log(result);
// })

// $("#addButton").click(function(){
//     if(results.includes(false)){
//         alert("필수 입력은 다 입력해");
//     }else{
//         alert("전송");
//         $("#addForm").submit();
//     }
// })