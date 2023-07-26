
window.onload=function () {
    let app = Vue.createApp({
        data(){
            return{
                cart:{},
                // 改变添加图书时按钮的状态，图书数量为1时，减号- 就消失
                disabled : false
            }
        },
        methods:{
            getCart:function () {
                axios({
                    method : "POST",
                    url : "cart.do",
                    params : {
                        operate : 'cartInfo'
                    }
                })
                    // 一定要用 => 表达式，不然this是无效的，也无法将data值发回到上面cart{}
                    .then((response) => {
                        // this.cart = response.data;
                        this.cart = response.data;
                    })
                    .catch(function (reason) {

                    });
            },
            editCart : function (cartItemId, buyCount){
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate : 'editCart',
                        cartItemId : cartItemId,
                        buyCount : buyCount
                    }
                }).then((response) =>  { // 一定要用 => 表达式，不然this是无效的，也无法调用getCart()
                    // this.getCart();

                    if (buyCount <= 0){
                        this.disabled = true;
                        buyCount = 1;
                    }else {
                        this.disabled = false;
                        this.getCart();
                    }

                }).catch(function (reason) { });
            }

        },
        mounted:function () {
            this.getCart();
        }
    });
    app.mount("#cart_div");

}