angular.module('app').controller('productsController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/mymarket';

    $scope.loadPage = function(pageIndex = 1) {
                $http({
                    url: contextPath + '/api/v1/products',
                    method: 'GET',
                    params: {
                    'p': pageIndex,
                    min_price: $scope.filter ? $scope.filter.min_price : null,
                    max_price: $scope.filter ? $scope.filter.max_price : null,
                    title: $scope.filter ? $scope.filter.title : null
                    }
                }).then(function (response) {
                    $scope.productsPage = response.data;
                    $scope.navList = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
                     console.log(response);
                });
            };
    $scope.addToCart = function(productId) {
                         $http({
                                url: contextPath + '/api/v1/cart/' + $localStorage.guestCartUuid + '/add/' + productId,
                                method: 'GET',
                                params: {}
                         }).then(function (response) {
                                  $scope.loadCart();
                         });
                     };
    $scope.generatePagesIndexes = function (startPage, endPage) {
                    let arr = [];
                    for (let i = startPage; i < endPage + 1; i++) {
                        arr.push(i);
                    }
                    return arr;
                }
    $scope.loadPage();
});