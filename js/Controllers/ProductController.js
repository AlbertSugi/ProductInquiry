app.controller('ProductController',['$scope',function($scope){
	$scope.products = {
		title: "Products available:",
		list:[]
	}
	$scope.categories = {
		title: "Categories:",
		list:[]
	}
	$scope.addItem = function(itemList,item){
		$scope.list.push($scope.addInventory);
	}

}])