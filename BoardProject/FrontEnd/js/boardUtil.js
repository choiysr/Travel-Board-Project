function appendHandlebars(source, data) {
    let tmp = Handlebars.compile(source);
    vals.$listDiv.append(tmp(data));
}

function prependHandlebars(data) {
    vals.$listDiv.prepend(vals.template(data));
}

function getList() {
    //카테고리랑 키워드 추가필요
    let category = $("select[name='category']").val();
    let keyword = $("input[name='keyword']").val();
    jsonService.getJson("http://localhost:8080/board/list", {
        category: category,
        keyword: keyword,
        lastBno: vals.lastBno,
        amount: 12
    }, success);

    function success(datas) {
        for (let i = 0; i < datas.length; i++) {
            let represent = datas[i].represent;
            let index = represent.indexOf("_");
            let fname = represent ? "v_"+represent.substring(index+1, datas[i].represent.length) : "null.jpg";
            let uploadpath = represent ? represent.substring(0,index) : "C://upload";

            datas[i].represent = {fname:fname, uploadpath:uploadpath};

            appendHandlebars(vals.listSource, datas[i]);

            read(datas[i].bno);
            if (i == datas.length - 1) vals.lastBno = datas[i].bno;
        }
    }

}

function getReply(bno) {
    // console.log("replyget"+bno);
    // let xml = new XMLHttpRequest();
    // xml.open("get", "http://localhost:8080/board/reply/"+bno+"?bno="+bno);
    // xml.onload = function () {
    //     let replys = "";
    //     let btn = "<p class='replyBtn' data-btn='replyWriteBtn'>답글</p><p class='replyBtn' data-btn='replyUpdateBtn'>수정</p><p class='replyBtn' data-btn='replyDeleteBtn'>삭제</p>";
    //     Array.from(JSON.parse(xml.response)).forEach(reply=> {
    //         replys += "<li data-rno='"+ reply.rno +"'><span class='replyChildReplyer'>"+reply.replyer+"</span><span class='replyChildReply'>"+reply.reply+"</span>"+btn+"</li><ul>";
    //         Array.from(reply.replyChildList).forEach(replyChild=>replys+="<li data-rno='"+replyChild.rno+"' data-pno='"+ replyChild.pno +"'><span class='replyChildReplyer'>"+replyChild.replyer+"</span><span class='replyChildReply'>"+replyChild.reply+"</span>"+btn+"</li>");
    //         replys += "</ul>";
    //     });
    //     $(".replyList").html(replys);
    // }
    // xml.send();

    jsonService.getJson("http://localhost:8080/board/reply",{bno:bno},success);

    function success() {
        let replys = "";
        let btn = "<p class='replyBtn' data-btn='replyWriteBtn'>답글</p><p class='replyBtn' data-btn='replyUpdateBtn'>수정</p><p class='replyBtn' data-btn='replyDeleteBtn'>삭제</p>";
        Array.from(JSON.parse(xml.response)).forEach(reply=> {
            replys += "<li data-rno='"+ reply.rno +"'><span class='replyChildReplyer'>"+reply.replyer+"</span><span class='replyChildReply'>"+reply.reply+"</span>"+btn+"</li><ul>";
            Array.from(reply.replyChildList).forEach(replyChild=>replys+="<li data-rno='"+replyChild.rno+"' data-pno='"+ replyChild.pno +"'><span class='replyChildReplyer'>"+replyChild.replyer+"</span><span class='replyChildReply'>"+replyChild.reply+"</span>"+btn+"</li>");
            replys += "</ul>";
        });
        $(".replyList").html(replys);
    }

}

function read(bno) {
    $("#" + bno).on("click", function () {
        jsonService.getJson("http://localhost:8080/board/"+bno,{bno:bno},success);

        function success(data){
            let downloadFiles = "";
            let replys = "";
            vals.$idTitle.text(data.title);
            vals.$idContent.html(data.content);
            vals.$bno.val(data.bno);
            Array.from(data.attachList).forEach(attach => {
                if (attach.isimg == 0){
                    let url = "http://localhost:8080/board/downloadFile?fname="+attach.fname+"&uploadpath="+attach.uploadpath;
                    downloadFiles += "<li class='replyLi'><a href='"+url+"'>" + attach.realname + "</a></li>";
                }
            });

            let btn = "<p class='replyBtn' data-btn='replyWriteBtn'>답글</p><p class='replyBtn' data-btn='replyUpdateBtn'>수정</p><p class='replyBtn' data-btn='replyDeleteBtn'>삭제</p>";
            Array.from(data.replyList).forEach(reply=> {
                replys += "<li data-rno='"+ reply.rno +"'><span class='replyChildReplyer'>"+reply.replyer+"</span><span class='replyChildReply'>"+reply.reply+"</span>"+btn+"</li><ul>";
                Array.from(reply.replyChildList).forEach(replyChild=>replys+="<li data-rno='"+replyChild.rno+"' data-pno='"+ replyChild.pno +"'><span class='replyChildReplyer'>"+replyChild.replyer+"</span><span class='replyChildReply'>"+replyChild.reply+"</span>"+btn+"</li>");
                replys += "</ul>";
                console.log(replys);
            });
            $(".attachment").html(downloadFiles);
            $(".replyList").html(replys);

        }

    });
}

function authChecking(data) {
    console.log(data)
    let result = 0;
    if(data.state==='LOGIN') {
        alert(data.msg);
        result=1;
        $("#moveLogin").trigger('click');
    }
    return result;
}