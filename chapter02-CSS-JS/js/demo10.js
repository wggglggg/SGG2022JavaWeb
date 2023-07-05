window.onload = function () {

    updateTotal()
    //当页面加载完成，我们需要绑定各种事件
    //根据id获取到表格

    var fruitTb = document.getElementById("tb_fruit");

    //获取表格中的所有的行
    var rows = fruitTb.rows;
    for (var i = 1; i < rows.length-1; i++) {
        var tr = rows[i];
        // 表格事件
        trBindEvent(tr);
    }
    // 绑定单击添加水果事件
    document.getElementById("add_btn").onclick=addFruit;
}

// 绑定表格事件
function trBindEvent(tr){

    //1.绑定鼠标悬浮以及离开时设置背景颜色事件
    tr.onmouseover=showBGColor;
    tr.onmouseout=clearBGColor;

    //获取tr这一行的所有单元格
    var cells = tr.cells;
    var price = cells[1];
    //2.绑定鼠标悬浮在单价单元格变手势的事件
    price.onmouseover = showHand;

    //3.绑定鼠标点击单价单元格的事件
    price.onclick=editPrice;

    //7.绑定删除小图标的点击事件
    var img = cells[4].firstChild;
    if (img && img.tagName == "IMG"){
        //绑定单击事件
        img.onclick = delFruit;
    }
}

//添加水果信息
function addFruit(){
    var fname = document.getElementById("fname").value;
    var fprice = parseInt(document.getElementById("fprice").value);
    var fcount = parseInt(document.getElementById("fcount").value);
    var eachTotal = fprice * fcount;

    var FruitTB = document.getElementById("tb_fruit");
    var tr = FruitTB.insertRow(FruitTB.rows.length-1);
    var fnameTD = tr.insertCell();
    fnameTD.innerText = fname;
    var fpriceTD = tr.insertCell();
    fpriceTD.innerText = fprice;
    var fcountTD = tr.insertCell();
    fcountTD.innerText = fcount;
    var eachTotalTD = tr.insertCell();
    eachTotalTD.innerText = eachTotal;

    var imgTD = tr.insertCell();
    imgTD.innerHTML = "<img src=\"imgs/del.jpg\" class=\"delImg\">"

    trBindEvent(tr);



    updateTotal();
}

function delFruit(){
    if (event && event.srcElement && event.srcElement.tagName == "IMG"){
        //alert表示弹出一个对话框，只有确定按钮
        //confirm表示弹出一个对话框，有确定和取消按钮。当点击确定，返回true，否则返回false
        if(window.confirm("是否确认删除当前库存记录")) {
            var fruitTB = document.getElementById("tb_fruit");

            var img = event.srcElement;
            var tr = img.parentElement.parentElement;

            fruitTB.deleteRow(tr.rowIndex)

            updateTotal();
        }
    }
}

//当鼠标点击单价单元格时进行价格编辑
function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
        var priceTD = event.srcElement;
        //目的是判断当前priceTD有子节点，而且第一个子节点是文本节点 ， TextNode对应的是3
        // ElementNode对应的是1
        if (priceTD.firstChild && priceTD.firstChild.nodeType == 3){
            //innerText 表示设置或者获取当前节点的内部文本
            var oldPrice = priceTD.innerText;
            //innerHTML 表示设置当前节点的内部HTML
            priceTD.innerHTML="<input type='text' size='4'/>";
            // <td><input type='text' size='4'/></td>
            var input = priceTD.firstChild;

            if (input.tagName == "INPUT"){
                input.value = oldPrice;
                //选中输入框内部的文本
                input.select();

                //4.绑定输入框失去焦点事件 , 失去焦点，更新单价
                input.onblur=updatePrice;

                //8.在输入框上绑定键盘摁下的事件，此处我需要保证用户输入的是数字
                input.onkeydown = checkInput;
            }
       }
    }
}

//检验键盘摁下的值的方法
function checkInput(){
    var keyCode = event.keyCode;
    // 0 ~ 9 : 48~57
    //backspace : 8
    //enter : 13
    //console.log(kc);

    if (!(keyCode >= 48 && keyCode <= 57 || keyCode == 8 || keyCode == 13)){
        event.returnValue = false;
    }
    if (keyCode == 13){
        event.srcElement.blur();
    }
}

function updatePrice() {
    if (event && event.srcElement && event.srcElement.tagName == "INPUT"){
        var input = event.srcElement;
        var newPrice = input.value;

        //input节点的父节点是td
        var priceTD = input.parentElement;
        priceTD.innerText = newPrice;

        //更新当前行的小计这一个格子的值
        //priceTD.parentElement td的父元素是tr
        updateEachTotal();
    }
}

//更新指定行的小计
function updateEachTotal(){
    /*if(tr && tr.tagName=="TR"){
		var tds = tr.cells;
		var price = tds[1].innerText ;
		var count = tds[2].innerText ;
		//innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
		var xj = parseInt(price) * parseInt(count);
		tds[3].innerText = xj ;

		//更新总计
		updateZJ();
	}*/

    var fruit_TB = document.getElementById("tb_fruit");
    var rows = fruit_TB.rows;

    for (var i = 1; i < rows.length-1; i++) {
        var tr = rows[i];

        var cells = tr.cells;
        var price = cells[1].innerText;
        var count = cells[2].innerText;

        //innerText获取到的值的类型是字符串类型，因此需要类型转换，才能进行数学运算
        var eachTotal = parseInt(price) * parseInt(count);

        cells[3].innerText = eachTotal;
    }
    updateTotal();
}

//更新总计
function updateTotal(){
    var fruitTB = document.getElementById("tb_fruit");

    var rows = fruitTB.rows;
    var sum = 0;

    for (var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        var eachTotal = parseInt(tr.cells[3].innerText);    //NaN    not a number 不是一个数字

        sum = sum + eachTotal;
    }
    rows[rows.length-1].cells[1].innerText = sum;
}

//当鼠标悬浮时，显示背景颜色
function showBGColor() {
    //event : 当前发生的事件
    //event.srcElement : 事件源
    //alert(event.srcElement);
    //alert(event.srcElement.tagName);	--> TD
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        //td.parentElement 表示获取td的父元素 -> TR
        var tr = td.parentElement;
        //如果想要通过js代码设置某节点的样式，则需要加上 .style
        tr.style.backgroundColor = "navy";

        //tr.cells表示获取这个tr中的所有的单元格
        var columns = tr.cells;
        for (var i = 0; i < columns.length; i++) {
            columns[i].style.color = "white";
        }
    }
}

//当鼠标离开时，恢复原始样式
function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "honeydew";

        var columns = tr.cells;
        for (var i = 0; i < columns.length; i++) {
            columns[i].style.color = "threeddarkshadow";
        }
    }
}

//当鼠标悬浮在单价单元格时，显示手势
function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        //cursor : 光标
        td.style.cursor = "hand";
    }
}