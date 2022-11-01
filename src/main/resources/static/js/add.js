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
