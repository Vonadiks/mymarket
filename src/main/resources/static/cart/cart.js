angular.module('app').controller('cartController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8080/mymarket';

    $scope.loadCart = function() {
                $http({
                    url: contextPath + '/api/v1/cart',
                    method: 'GET',
                    params: {}
                }).then(function (response) {
                    $scope.cart = response.data;
                });
            };
    $scope.decrementCartPosition = function(productId) {
                              $http({
                                     url: contextPath + '/api/v1/cart/decrement/' + productId,
                                     method: 'GET',
                                     params: {}
                              }).then(function (response) {
                                       $scope.loadCart();
                              });
                          };
    $scope.incrementCartPosition = function(productId) {
                                   $http({
                                          url: contextPath + '/api/v1/cart/add/' + productId,
                                          method: 'GET',
                                          params: {}
                                   }).then(function (response) {
                                            $scope.loadCart();
                                   });
                               };

    $scope.addToCart = function(productId) {
                         $http({
                                url: contextPath + '/api/v1/cart/add/' + productId,
                                method: 'GET',
                                params: {}
                         }).then(function (response) {
                                  $scope.loadCart();
                         });
                     };
     $scope.clear = function() {
                     $http({
                         url: contextPath + '/api/v1/cart/clear',
                         method: 'GET',
                         params: {}
                     }).then(function (response) {
                         $scope.cart=null;
                     });
                 };

      $scope.createOrder = function() {
            $http({
                url: contextPath + '/api/v1/orders',
                method: 'POST',
                params: {
                    phone: $scope.order_info.phone,
                    address: $scope.order_info.address
                }
            }).then(function(response) {
                alert('Заказ создан');
                $scope.loadCart();
            });
        };

//    $scope.createOrder = function () {
//                    $http.post(contextPath + '/api/v1/orders', $scope.user)
//                     .then(function (response) {
//                         alert('Заказ создан');
//                         $scope.loadCart();
//                        // $scope.loadOrders();
//                     });
//                 };
     $scope.deleteProductFromCart = function(productId) {
                         $http({
                                url: contextPath + '/api/v1/cart/delete/' + productId,
                                method: 'GET',
                                params: {}
                         }).then(function (response) {
                                 console.log(response);
                                  //$scope.cart = response.data;
                                 $scope.loadCart();
                         });

                     };
      $scope.createOrder = function () {
             $http({
                 url: contextPath + '/api/v1/orders',
                 method: 'POST',
                 params: {
                     phone: $scope.order_info.phone,
                     address: $scope.order_info.address
                 }
             }).then(function successCallback(response) {
                 alert('Заказ создан');
                 $scope.loadCart();
             }, function errorCallback(response) {
                 alert(response.data.messages);
             });
         }

    $scope.loadCart();

});