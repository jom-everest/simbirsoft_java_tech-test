'use strict';

angular.module('App').controller('PersonController', ['$scope', 'PersonService', function($scope, PersonService) {
    var self = this;
    self.person = {id: null, fio: '', developer: '', email: '', tagsStr: ''};
    self.persons = [];
	
	self.tags = [];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
	
	
    fetchAllPersons();
//	fetchAllTags();
//	setCloudSettings();
	
/*
	function fetchAllTags() {
		TagService.fetchAllTags()
			.then(function(tags) {
				self.tags = tags;
			},
			function(errResponse) {
                console.error('Error while fetching Tags');
			}
		);
	}

	function fetchTag(tagId) {
		TagService.fetchTag(tagId)
            .then(function(tag) {
                self.tag = tag;
            },
            function(errResponse){
                console.error('Error while fetching Tag');
            }
        );
	}
*/	
    function fetchAllPersons(){
        PersonService.fetchAllPersons()
            .then(function(persons) {
                self.persons = persons;
				self.persons.forEach(function(p) {
					p.tagsStr = p.tags.map(function(tag) {return tag.name;}).join(', ');
				});
            },
            function(errResponse){
                console.error('Error while fetching Persons');
            }
        );
    }
	
	function fetchPerson(id) {
		PersonService.fetchPerson(id)
            .then(function(person) {
                self.person = person;
            },
            function(errResponse){
                console.error('Error while fetching Person');
            }
        );
	}

    function createPerson(person){
		person.tags = person.tagsStr.split(',').map(function(s) { return s.trim()});
        PersonService.createPerson(person)
            .then(
            fetchAllPersons,
            function(errResponse){
                console.error('Error while creating Person');
            }
        );
    }

    function updatePerson(id, person){
		person.tags = person.tagsStr.split(',').map(function(s) { return s.trim()});
        PersonService.updatePerson(id, person)
            .then(
            fetchAllPersons,
            function(errResponse){
                console.error('Error while updating Person');
            }
        );
    }
	
    function deletePerson(id){
        PersonService.deletePerson(id)
            .then(
			fetchAllPersons,
            function(errResponse){
                console.error('Error while deleting Person');
            }
        );
    }

    function submit() {
        if(self.person.id === null){
            console.log('Saving New Person', self.person);
            createPerson(self.person);
        }else{
            updatePerson(self.person.id, self.person);
            console.log('Person updated with id ', self.person.id);
        }
        reset();
    }
	
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.persons.length; i++){
            if(self.persons[i].id === id) {
                self.person = angular.copy(self.persons[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.person.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deletePerson(id);
    }

	function reset(){
        self.person = {id: null, fio: '', developer: 'Java', email: '', hobbies: 'extreme', tagsStr:''};
        $scope.myForm.$setPristine(); //reset Form
    }

/*	
	function setCloudSettings() {
		var entries;
		for(var i = 0; i < self.tags.length; i++) {
			entries[i].label = self.tags[i].name;
			entries[i].url = "http://127.0.0.1:8080/tag/" + self.tags[i].name;
			entries[i].target = '_top';
		}
		self.setting.entries = entries;
	}
*/	
}]);
