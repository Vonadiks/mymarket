angular.module('app', []).controller('indexController', function ($scope, $http) {
    $scope.loadProducts = function() {
        $http({
            url: 'http://localhost:8080/mymarket/products',
            method: 'GET',
            params: {}
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };
        $scope.counterValue=1;
        $scope.clickIncrementButton = function() {
        $scope.counterValue+=1
        };

        $scope.loadProducts();

        $scope.showProductInfo = function(productIndex) {
                $http({
                    url: 'http://localhost:8080/mymarket/products/' + productIndex,
                    method: 'GET',
                    params: {}
                }).then(function (response) {
                    console.log(response);
                    alert(response.data.title);
                });
            };


            $scope.deleteProductById = function(productIndex) {
                $http({
                       url: 'http://localhost:8080/mymarket/products/delete/' + productIndex,
                       method: 'GET',
                       params: {}
                }).then(function (response) {
                        console.log(response);
                         $scope.products = response.data;
                });

            };




});