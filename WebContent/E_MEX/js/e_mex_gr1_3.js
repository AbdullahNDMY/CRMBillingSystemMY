(function($) {
	// ready
	$(document).ready(function() {
		$("input[name='giroDay']").spinbox({
			min: 1,
			max: 31
		});
		$("input[name='giroMonth']").spinbox({
			min: 1,
			max: 12
		});
		$("input[name='giroYear']").spinbox({
			min: 1,
			max: 9999
		});
	});
	
})(jQuery);