var app = new Vue({
    el: '#app',
    data: {
        productId: '',
        productCode: '',
        productName: '',
        price: '',
        discount: '',
        description: '',
        stockQuantity: '',
        mainPicUrl: '',
        otherPicUrls: [],
        quantity: 1,
        myShoppingCart: []
    },
    mounted() {
        this.productId=45;
        var myShoppingCartJson = localStorage['myShoppingCart'];
        this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
    },
    methods: {
        handleAddToCartClick(){
            var myShoppingCartJson = localStorage['myShoppingCart'];
            this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
            var  cartProductId=this.myShoppingCart.find(p=>p.productId=this.productId);
            if(cartProductId){
                var originQuantity = parseInt(cartProductId.quantity);
                var addQuantity = parseInt(this.quantity);
                cartProductId.quantity = originQuantity + addQuantity;
            }else{
                cartProduct = {
                    productId: 45,
                    productCode: 'pp02',
                    productName: 'homes',
                    mainPicUrl: 'http/aaaa.jpg',
                    unitPrice: 456.46,
                    quantity: 1
                };
                this.myShoppingCart.push(cartProduct);
            }
            localStorage['myShoppingCart'] =  JSON.stringify(this.myShoppingCart)
        }
    }
})