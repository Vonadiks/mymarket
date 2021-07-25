angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/mymarket';
    $scope.loadPage = function(pageIndex = 1) {
            $http({
                url: contextPath + '/api/v1/products',
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
            url: contextPath + '/api/v1/products',
            method: 'GET',
            params: {}
        }).then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };

        $scope.loadProducts();

        $scope.showProductInfo = function(productIndex) {
                $http({
                    url: contextPath + '/api/v1/products/' + productIndex,
                    method: 'GET',
                    params: {}
                }).then(function (response) {
                    console.log(response);
                    alert(response.data.title);
                });
            };


            $scope.deleteProductById = function(productIndex) {
                $http({
                       url: contextPath + '/api/v1/products/' + productIndex,
                       method: 'GET',
                       params: {}
                }).then(function (response) {
                        console.log(response);
                         $scope.products = response.data;
                });

            };




});