var vals = (function () {
    let lastBno = 0;
    let $bno = $("input[name='bno']");
    let $close = $("button[name='closeBtn']");
    let $writeBtn = $("#writeBtn");
    let $listDiv = $("#listDiv");
    let $window = $(window);
    let $idTitle = $("#title");
    let $idContent = $("#content");
    let $nameTitle = $("input[name='title']");
    let $textDiv = $("#textDiv");
    let $fileList = $("#fileList");
    let $imgList = $("#imgList");
    let $inputImg = $("#images");
    let $inputFile = $("#files");

    let listSource = $("#entry-template").html();


    return {
        lastBno: lastBno,
        $bno: $bno,
        $close: $close,
        $writeBtn: $writeBtn,
        $listDiv: $listDiv,
        $window: $window,
        $idTitle: $idTitle,
        $idContent: $idContent,
        $nameTitle: $nameTitle,
        $textDiv: $textDiv,
        $fileList: $fileList,
        $imgList: $imgList,
        $inputImg:$inputImg,
        $inputFile:$inputFile,
        listSource: listSource
    }
})();