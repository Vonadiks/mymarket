angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/mymarket/api/v1';
    $scope.loadPage = function(pageIndex = 1) {
            $http({
                url: contextPath + '/products',
                method: 'GET',
                params: {
                'p': pageIndex
                }
            }).then(function (response) {
                $scope.productsPage = response.data;
                $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
                 console.log(response);
            });
        };
        $scope.loadPage();

        $scope.generatePagesIndexes = function (startPage, endPage) {
                let arr = [];
                for (let i = startPage; i < endPage + 1; i++) {
                    arr.push(i);
                }
                return arr;
            }

    $scope.loadProducts = function() {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {}
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };

     $scope.loadCart = function() {
            $http({
                url: contextPath + '/cart',
                method: 'GET',
                params: {}
            }).then(function (response) {
                $scope.cart = response.data;
            });
        };

        $scope.loadCart();


        $scope.deleteProductById = function(productIndex) {
                $http({
                       url: contextPath + '/products/' + productIndex,
                       method: 'DELETE',
                       params: {}
                }).then(function (response) {
                        console.log(response);
                         //$scope.products = response.data;
                         $scope.loadProducts();
                         //$scope.loadPage;

                });
            };

     $scope.addToCart = function(productId) {
                     $http({
                            url: contextPath + '/cart/add/' + productId,
                            method: 'GET',
                            params: {}
                     }).then(function (response) {
                              $scope.loadCart();
                     });
                 };

     $scope.loadProducts();

       $scope.deleteProductFromCart = function(productId) {
                     $http({
                            url: contextPath + '/cart/delete/' + productId,
                            method: 'DELETE',
                            params: {}
                     }).then(function (response) {
                             console.log(response);
                              $scope.cart = response.data;
                             $scope.loadCart();
                     });

                 };

       $scope.clear = function() {
                 $http({
                     url: contextPath + '/cart/clear',
                     method: 'DELETE',
                     params: {}
                 }).then(function (response) {
                     $scope.loadCart();
                 });
             };

             $scope.loadCart();

});