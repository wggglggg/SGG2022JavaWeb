function delFruit(fid, pageNo){
    window.location.href='fruit.do?fid='+fid+'&pageNo='+pageNo+'&operate=del';
}

function page(pageNo){
    window.location.href='fruit.do?pageNo='+pageNo;
}