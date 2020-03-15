
function updateEvent() {
    jsonService.updateJson("http://localhost:8080/board/update", {
        bno: vals.$bno.val(),
        title: vals.$nameTitle.val(),
        content: vals.$nameContent.val()
    }, success);

    function success(datas) {
        alert(datas.msg);
        if (datas.state === "SUCCESS") {
            //작성자 추가시 text변경 필요
            $("#"+vals.$bno.val()).find('p').text(vals.$bno.val()).siblings('span').text(vals.$nameTitle.val()).siblings('i').text('text');
            read({bno: vals.$bno.val(), title: vals.$nameTitle.val(), content: vals.$nameContent.val()});
            vals.$close.click();
        }
    }
}

function writeEvent() {
    let attachList = new Array();
    let represent = "";
    let cnt = 0;
    $(".boardFile").each(function (index, file) {
        attachList.push({fname:file.getAttribute('data-fname'), uploadpath:file.getAttribute('data-uploadpath'), isimg:0});
    });

    $(".boardImg").each(function (index, img) {
        if(img.getAttribute('data-represent') == 1){
            represent = img.getAttribute('data-uploadpath')+"_"+ img.getAttribute('data-fname');
            cnt++;
        }
        attachList.push({fname:img.getAttribute('data-fname'), uploadpath:img.getAttribute('data-uploadpath'), isimg:1});
    });
    if(cnt == 0) represent = $(".boardImg").first().attr('data-uploadpath')+"_"+$(".boardImg").first().attr('data-fname');

    jsonService.postJson("http://localhost:8080/board/write", {
        title: vals.$nameTitle.val(),
        content: vals.$textDiv.html(),
        represent : represent,
        attachList : attachList
    }, success);

    function success(datas) {
        alert(datas.msg);
        if (datas.state === "SUCCESS") {
            vals.$close.click();
        }
    }
}

function deleteEvent(){
    let bno = vals.$bno.val();
    jsonService.deleteJson("http://localhost:8080/board/" + bno, {bno: bno}, success);

    function success(datas) {
        alert(datas.msg);
        $("#" + bno).remove();
        vals.$close.click();
    }
}

vals.$imgList.on('click', '.liThumbnail', function () {
    vals.$imgList.find('.removeFile').each(function (index, thumbnail) {
        thumbnail.setAttribute('data-represent',0);
        thumbnail.parentNode.setAttribute('style','background-color:white');
    });
    let x = $(this).siblings('.removeFile').attr('data-fname');
    $(this).siblings('.removeFile').attr('data-represent',1).parent().attr('style','background-color:pink');
    vals.$textDiv.find('img').each(function (index, img) {
        img.getAttribute('data-fname') === x ? img.setAttribute('data-represent',1) : img.setAttribute('data-represent',0);
    });
});

vals.$imgList.on('click', '.removeFile', function () {
    $(this).parent().remove();
    $("img[data-fname='" + $(this).attr('data-fname') + "']").remove();
    representCheck();
});

vals.$fileList.on('click', '.removeFile', function () {
    $(this).parent().remove();
});

function representCheck() {
    let cnt = 0;
    vals.$imgList.find('.removeFile').each(function (index, iTag) {
        if(iTag.getAttribute('data-represent') == 1)    cnt++;
    });
    if(cnt == 0)
        vals.$imgList.find('li').first().attr('style','background-color: pink').children('.removeFile').attr('data-represent',1);
}