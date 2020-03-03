var app = new Vue({
    el: '#app',
    data: {
        myProducts: []
    },
    mounted() {
        var myShoppingCart = localStorage['myShoppingCart'];
        console.log(myShoppingCart);
      this.myProducts=JSON.parse(myShoppingCart);
    },
    methods: {
        handleDelete(index, row){
            this.myProducts.splice(index, 1);
            localStorage['myShoppingCart'] = JSON.stringify(this.myProducts);

        },
        handleUpdate(){
            localStorage['myShoppingCart'] = JSON.stringify(this.myProducts);
        }
    },
})