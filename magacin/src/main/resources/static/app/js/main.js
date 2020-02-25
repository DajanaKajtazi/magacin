var magacinApp = angular.module("magacinApp", ['ngRoute']);

magacinApp.config(['$routeProvider', function($routeProvider) {
	
	$routeProvider.when('/artikli', {
		templateUrl: '/app/html/artikli.html'
	}).when('/artikli/add', {
		templateUrl:'/app/html/add-artikal.html'
	}).when('/artikli/edit/:id', {
		templateUrl:'/app/html/edit-artikal.html'
	}).when('/artikli/stavka/:id', {
		templateUrl:'/app/html/add-stavka.html'
	}).otherwise({
		redirectTo:'/'
	});
	
}]);


magacinApp.controller("ArtikliCtrl", function($scope, $http, $location) {
	
	var url = "/api/artikli";
	var mUrl = "/api/magacini";
	
	$scope.artikli = [];
	$scope.magacini = [];
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	$scope.searchArtikal = {};
	$scope.searchArtikal.naziv = "";
	$scope.searchArtikal.magacinId = "";
	
	
	var getArtikle = function() {
		
		var config = {params: {}};
		
		config.params.pageNum = $scope.pageNum;
		
		if( $scope.searchArtikal.naziv != "" ){
			config.params.naziv = $scope.searchArtikal.naziv;
		}
		
		if( $scope.searchArtikal.magacinId != "" ){
			config.params.magacinId = $scope.searchArtikal.magacinId;
		}
		
		$http.get(url, config).then(
				function success(res) {
					$scope.artikli = res.data;
					$scope.totalPages = res.headers("totalPages");
					
				}, function error() {
					alert("Nije moguce dobaviti artikle!");
				}
		);
	}
	
	getArtikle();
	
	var getMagacine = function() {
	
		var promise = $http.get(mUrl);
		promise.then(
		function success(res) {
			$scope.magacini = res.data;
			
		}, function error() {
			alert("Nije moguce dobaviti magacine!");
		}		
		);
	}
	
	getMagacine();
	
	$scope.goToAdd = function() {
		$location.path("/artikli/add");
	}
	
	$scope.goToDodavanje = function(id) {
		$location.path("/artikli/stavka/"+id);
	}
	$scope.changePage = function(d) {
		$scope.pageNum += d;
		getArtikle();
	}
	
	$scope.doSearch = function() {
		$scope.pageNum = 0;
		getArtikle();
	}
	
	$scope.doDelete = function(id) {
		var promise = $http.delete(url+"/"+id);
		promise.then(
		function succes() {
			getArtikle();
		}, function error() {
			alert("Nije moguce obrisati artikal!");
		}		
		);
	}
	
	$scope.goToEdit = function(id) {
		$location.path("/artikli/edit/" + id);
	}
	
	
});



magacinApp.controller("AddCtrl", function($scope, $http, $location) {
	
	var url = "/api/artikli";
	var mUrl = "/api/magacini";
	
	$scope.artikal = {};
	$scope.artikal.naziv = "";
	$scope.artikal.pakovanje = "";
	$scope.artikal.jedinicaMere = "";
	$scope.artikal.kolicina = "0";
	$scope.artikal.kalkulisanaCena = "0";
	$scope.artikal.magacinId = "";
	
	$scope.magacini = [];
	
	var getMagacine = function() {
		
		$http.get(mUrl).then(
				function success(res) {
					$scope.magacini = res.data;
				}, function error(){
					alert("Nije moguce dobaviti magacine!");
				}
				);
	}
	
	getMagacine();
	
	$scope.doAdd = function() {
		$http.post(url, $scope.artikal).then(
		function success() {
			$location.path("/artikli");
			
			$scope.artikal = {};
			$scope.artikal.naziv = "";
			$scope.artikal.pakovanje = "";
			$scope.artikal.jedinicaMere = "";
			$scope.artikal.kolicina = "0";
			$scope.artikal.kalkulisanaCena = "0";
			$scope.artikal.magacinId = "";
			
		}, function error() {
			alert("Nije moguce sacuvati artikal!");
		}		
		);
	}
});

magacinApp.controller("EditCtrl", function($scope, $http, $routeParams, $location) {

var aUrl = "/api/artikli/" + $routeParams.id;
var maUrl = "/api/magacini";

$scope.magacini = [];

$scope.artikal = {};
$scope.artikal.naziv = "";
$scope.artikal.pakovanje = "";
$scope.artikal.jedinicaMere = "";
$scope.artikal.kolicina = "";
$scope.artikal.kalkulisanaCena = "";
$scope.artikal.magacinId = "";


     var getMagacin = function() {
		
    	 var promise = $http.get(maUrl);
    	 promise.then(
    	function success(res) {
			$scope.magacini = res.data;
    		
		}, function error() {
			alert("Nije moguce dobaviti magacin!");
		}		 
    	 );
	}
     
     getMagacin();
	
	var getArtikal = function() {
		
		$http.get(aUrl).then(
		function success(res) {
			$scope.artikal = res.data;
		}, function error() {
			alert("Nije moguce dobaviti artikal!");
		}		
		);
	}
	
	getArtikal();
	
	$scope.doEdit = function() {
		$http.put(aUrl, $scope.artikal).then(
		function success() {
			$location.path("/artikli");
		}, function error() {
			alert("Nije moguce izvrsiti izmenu artikla!");
		}		
		);
	}
});

magacinApp.controller("StavkaCtrl", function($scope, $http, $routeParams, $location) {
	
	var stavke= "/api/artikli/" + $routeParams.id + "/stavka" ;
	
	
	
	$scope.stavka = {};
	$scope.stavka.kolicina = "";
	$scope.stavka.jedinicnaCena = "";
	
	
	$scope.doDodavanje = function() {
		$http.post(stavke, $scope.stavka).then(
		function success() {
			
			$location.path("/artikli");
			
		}, function error() {
			alert("Nije moguce sacuvati stavku!");
		}		
		);
	}
	
});