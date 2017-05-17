$(document).ready(function() {

	$(".file-dropzone").on('dragover', handleDragEnter);
	$(".file-dropzone").on('dragleave', handleDragLeave);
	$(".file-dropzone").on('drop', handleDragLeave);

	function handleDragEnter(e) {

		this.classList.add('drag-over');
	}

	function handleDragLeave(e) {

		this.classList.remove('drag-over');
	}

	Dropzone.options.dropzoneForm = {

		url : "/uploadFile",
		autoProcessQueue : false,
		uploadMultiple : true,
		maxFilesize : 256, // MB
		parallelUploads : 100,
		maxFiles : 100,
		addRemoveLinks : true,
		previewsContainer : ".dropzone-previews",

		init : function() {
			var myDropzone = this;
			$('#upload-button').on("click", function(e) {
				myDropzone.processQueue();
			});
			this.on("uploadprogress", function(file, progress) {
				progress = parseFloat(progress).toFixed(0);
				var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
				progressBar.innerHTML = progress + "%";
			});
			this.on("successmultiple", function(files, serverResponse) {
				showInformationDialog(files, serverResponse);
			});
		}
	};

	function showInformationDialog(files, objectArray) {
/*

		var responseContent = "";

		for (var i = 0; i < objectArray.length; i++) {

			var infoObject = objectArray[i];

			for ( var infoKey in infoObject) {
				if (infoObject.hasOwnProperty(infoKey)) {
					responseContent = responseContent + " " + infoKey + " -> " + infoObject[infoKey] + "<br>";
				}
			}
			responseContent = responseContent + "<hr>";
		}
*/

		// from the library bootstrap-dialog.min.js
/*		BootstrapDialog.show({
			title : '<b>Server Response</b>',
			message : responseContent
		});*/
	}

});