function detailScreen(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        area: ['70%', '70%'],
        content: url,
        maxmin: true,
        end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
            location.reload();　　//layer.open关闭刷新
        }
    });
    layer.full(index);

}

function commentScreen(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        area: ['70%', '70%'],
        content: url,
        maxmin: true,
        end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
            location.reload();　　//layer.open关闭刷新
        }
    });
    layer.full(index);
}

function editScreen(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        area: ['70%', '70%'],
        content: url,
        maxmin: true,
        end: function () {//无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数。layer.open关闭事件
            location.reload();　　//layer.open关闭刷新
        }
    });
    layer.full(index);
}

function ifdelete(id,title) {
    layer.confirm('确定删除该文章吗?', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: 'POST',
            url: '/article/del',
            datatype:'json',
            data:{"id":id},
            success: function(data) {
                if (data.status == "200") {
                    layer.msg('删除成功!',{icon:1,time:1000});
                    setTimeout("window.location.reload()",1000);
                }else {
                    layer.msg('删除失败!',{icon:5,time:1000});
                }
            },
            error:function(data) {
                console.log('错误...');
            },
        });
    }, function(){

    });
}

/**
 * 检查提交是否为空
 * @param form1
 * @returns {boolean}
 */
function beforeSubmit(form1) {
    if(form.keyword.value==''){
        alert("搜索词为空哦！");
        form.keyword.focus();
        return false;
    }
}

//定义一个全局变量
var isFalg=false;
/**
 * 防止表单多提交事件
 */
function checkFrom() {
    //为false表示表单未提交过
    if (isFalg==false){
        isFalg=true;
        return true;
    }else {
        return false;
    }
}

