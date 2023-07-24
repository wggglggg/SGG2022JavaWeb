

function $(id) {
    return document.getElementById(id);
}

function checkRegister() {
    //用户名应为6~16位数组和字母组成
    let name = $("nameTxt").value;
    let nameSpan = $("nameSpan");

    let nameReg = /\w{6,16}/;

    if(!nameReg.test(name)){
        nameSpan.style.visibility="visible"
        return false;
    } else {
        nameSpan.style.visibility="hidden"
    }

    //密码的长度至少为8位
    let pwd = $("pwdTxt").value;
    let pwdSpan = $("pwdSpan");

    let pwdReg = /.{8,}/;

    if (!pwdReg.test(pwd)){
        pwdSpan.style.visibility="visible";
        return false;
    } else {
        pwdSpan.style.visibility="hidden"
    }

    //密码两次输入不一致
    let pwdConfirm = $("pwdConfirm").value;
    let pwd2Span = $("pwd2Span");
    if (!pwdConfirm == pwd){
        pwd2Span.style.visibility="visible";
        return false;
    } else {
        pwd2Span.style.visibility="hidden"
    }

    //请输入正确的邮箱格式
    let email = $("emailTxt").value;
    let emailSpan = $("emailSpan");

    // let emailReg = /^[a-zA-Z0-9_\.-]+@[a-zA-Z0-9-]+[\.]{1}+[a-zA-Z]+$/;
    let emailReg = /^[a-zA-Z0-9_.-]+@([a-zA-Z0-9-]+[.]{1})+[a-zA-Z]+$/;

    if (!emailReg.test(email)){
        emailSpan.style.visibility='visible';
        return false;
    } else {
        emailSpan.style.visibility="hidden"
    }

    return true;
}
//
// let xmlHttpRequest;
// function ckUname(uname) {
//     createXMLHttpRequest();
//     let url = "user.do?operate=ckUname&uname=" + uname;
//
//     xmlHttpRequest.open("GET", url, true);
//     //设置回调函数
//
//     xmlHttpRequest.onreadystatechange = ckUnameCB;
//
// }
// function createXMLHttpRequest() {
//     if (window.XMLHttpRequest){
//         //符合DOM2标准的浏览器 ，xmlHttpRequest的创建方式
//         xmlHttpRequest = new XMLHttpRequest();
//     } else if (window.ActiveXObject){
//         //IE浏览器
//         try {
//             xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
//         } catch (e) {
//             xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
//         }
//
//     }
// }
//
// function ckUnameCB() {
//     if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
//         let sameName;
//         let sameName2;
//         //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
//         //alert(xmlHttpRequest.responseText);
//         let responseText = xmlHttpRequest.responseText;
//
//         if (responseText == {'uname':'1'}){
//             // sameName = $('ckNameId');
//             // sameName.style.visibility = "visible";
//             alert("用户名被占用");
//         }else if (responseText == {'uname':'0'}){
//             // sameName2 = $('ckNameId2');
//             // sameName2.style.visibility = "visible";
//             alert("用户名可用！");
//         }
//         // sameName.style.visibility = "hidden";
//         // sameName2.style.visibility = "hidden";
//
//     }
// }
//如果想要发送异步请求，我们需要一个关键的对象 XMLHttpRequest
var xmlHttpRequest ;

function createXMLHttpRequest(){
    if(window.XMLHttpRequest){
        //符合DOM2标准的浏览器 ，xmlHttpRequest的创建方式
        xmlHttpRequest = new XMLHttpRequest() ;
    }else if(window.ActiveXObject){//IE浏览器
        try{
            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e) {
            xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP")
        }
    }
}

function ckUname(uname){
    createXMLHttpRequest();
    var url = "user.do?operate=ckUname&uname="+uname ;
    xmlHttpRequest.open("GET",url,true);
    //设置回调函数
    xmlHttpRequest.onreadystatechange = ckUnameCB ;
    //发送请求
    xmlHttpRequest.send();
}

function ckUnameCB(){
    if(xmlHttpRequest.readyState==4 && xmlHttpRequest.status==200){
        //xmlHttpRequest.responseText 表示 服务器端响应给我的文本内容
        //alert(xmlHttpRequest.responseText);
        var responseText = xmlHttpRequest.responseText ;
        // {'uname':'1'}
        alert(responseText);
        if(responseText==="{'uname':'1'}"){
            alert("用户名已经被注册！");
        }else{
            alert("用户名可以注册！");
        }
    }
}

