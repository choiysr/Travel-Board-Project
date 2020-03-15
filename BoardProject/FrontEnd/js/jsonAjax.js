var jsonService = (function () {

    function postJson(url, data, success, fail) {
        excuteAjax("post", url, data, success, fail);
    }

    function deleteJson(url, data, success, fail) {
        excuteAjax("delete", url, data, success, fail);
    }

    function updateJson(url, data, success, fail) {
        excuteAjax("put", url, data, success, fail);
    }

    function getJson(url, data, success, fail) {
        $.ajax({
            url: url,
            type: "GET",
            data: data,
            dataType: "json",
            contentType: "application/json"
        }).done(function (datas) {
            if(authChecking(datas)==1) return;
            success(datas);
        });
    }

    function excuteAjax(type, url, data, success, fail) {
        $.ajax({
            url: url,
            type: type,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json"
        }).done(function (datas) {
            if(authChecking(datas)==1) return;
            success(datas);
        });
    }

    return {postJson: postJson,
            deleteJson: deleteJson,
            updateJson: updateJson,
            getJson: getJson
    };
})();

var fileService = (function () {
    
    function uploadFile(url, formData, success, fail) {
        fileAjax("post", url, formData, success, fail);
    }
    
    
    function fileAjax(type, url, formData, success, fail) {
        $.ajax({
            url: url,
            processData: false,
            contentType: false,
            data: formData,
            type: type
        }).done(function (datas) {
          success(datas);
        });
    }

    return {uploadFile: uploadFile};

})();