"use strict";

(function($, sr) {
	var debounce = function(func, threshold, execAsap) {
		var timeout;

		return function debounced() {
			var obj = this, args = arguments;
			function delayed() {
				if (!execAsap)
					func.apply(obj, args);
				timeout = null;
			}

			if (timeout)
				clearTimeout(timeout);
			else if (execAsap)
				func.apply(obj, args);

			timeout = setTimeout(delayed, threshold || 100);
		};
	};

	// smartresize
	jQuery.fn[sr] = function(fn) {
		return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
	};

})(jQuery, 'smartresize');

// Sidebar
$(document).ready(
		function() {
			// TODO: This is some kind of easy fix, maybe we can improve this
			var setContentHeight = function() {
				// reset height
				$('.right_col').css('min-height', $(window).height());

				var bodyHeight = $('body').outerHeight();
				var footerHeight = $('body').hasClass('footer_fixed') ? -10
						: $('footer').height();
				var leftColHeight = $('.left_col').eq(1).height()
						+ $('.sidebar-footer').height();
				var contentHeight = bodyHeight < leftColHeight ? leftColHeight
						: bodyHeight;

				// normalize content
				contentHeight -= $('.nav_menu').height() + footerHeight;

				$('.right_col').css('min-height', contentHeight - 35);
			};

			var collapsedAction = function() {
				// Collapsed action
				$('#sidebar-menu').find('li.active ul').hide();
				$('#sidebar-menu').find('li.active').addClass('active-sm')
						.removeClass('active');
				window.localStorage.collapsedMenu = true;
			}
			if (window.localStorage.collapsedMenu
					&& window.localStorage.collapsedMenu == 'true') {
				collapsedAction();
				$('body').toggleClass('nav-md nav-sm');
				setContentHeight();
			} else {
				window.localStorage.collapsedMenu = false;
			}

			$('#sidebar-menu').find('a').click(
					function(ev) {
						var $li = $(this).parent();

						if ($li.is('.active')) {
							$li.removeClass('active active-sm');
							$('ul:first', $li).slideUp(function() {
								setContentHeight();
							});
						} else {
							// prevent closing menu if we are on child menu
							if (!$li.parent().is('.child_menu')) {
								$('#sidebar-menu').find('li').removeClass(
										'active active-sm');
								$('#sidebar-menu').find('li ul').slideUp();
							}

							$li.addClass('active');

							$('ul:first', $li).slideDown(function() {
								setContentHeight();
							});
						}
					});

			// toggle small or large menu
			$('#menu_toggle').on(
					'click',
					function() {
						if ($('body').hasClass('nav-md')) {
							collapsedAction();
						} else {
							// No collapsed action
							$('#sidebar-menu').find('li.active-sm ul').show();
							$('#sidebar-menu').find('li.active-sm').addClass(
									'active').removeClass('active-sm');
							window.localStorage.collapsedMenu = false;
						}
						$('body').toggleClass('nav-md nav-sm');
						setContentHeight();
					});

			// check active menu
			$('#sidebar-menu').find(
					'a[href="'
							+ window.location.href.split('#')[0].split('?')[0]
							+ '"]').parent('li').addClass('current-page');

			var elements = $('#sidebar-menu').find('a').filter(
					function() {
						return this.href == window.location.href.split('#')[0]
								.split('?')[0];
					}).parent('li').addClass('current-page');

			if (window.localStorage.collapsedMenu
					&& window.localStorage.collapsedMenu == 'true') {
				elements.parents('ul').parent().addClass('active');
			} else {
				elements.parents('ul').slideDown(function() {
					setContentHeight();
				}).parent().addClass('active');
			}

			// recompute content when resizing
			$(window).smartresize(function() {
				setContentHeight();
			});

			setContentHeight();

			// fixed sidebar
			if ($.fn.mCustomScrollbar) {
				$('.menu_fixed').mCustomScrollbar({
					autoHideScrollbar : true,
					theme : 'minimal',
					mouseWheel : {
						preventDefault : true
					}
				});
			}
		});
// /Sidebar

// Panel toolbox
$(document)
		.ready(
				function() {
					$('.collapse-link')
							.on(
									'click',
									function() {
										var $BOX_PANEL = $(this).closest(
												'.x_panel'), $ICON = $(this)
												.find('i'), $BOX_CONTENT = $BOX_PANEL
												.find('.x_content');

										// fix for some div with hardcoded fix
										// class
										if ($BOX_PANEL.attr('style')) {
											$BOX_CONTENT
													.slideToggle(
															200,
															function() {
																$BOX_PANEL
																		.removeAttr('style');
															});
										} else {
											$BOX_CONTENT.slideToggle(200);
											$BOX_PANEL.css('height', 'auto');
										}

										$ICON
												.toggleClass('fa-chevron-up fa-chevron-down');
									});

					$('.close-link').click(function() {
						var $BOX_PANEL = $(this).closest('.x_panel');

						$BOX_PANEL.remove();
					});
				});
// /Panel toolbox

// Accordion
$(document).ready(function() {
	$(".expand").on("click", function() {
		$(this).next().slideToggle(200);
		$expand = $(this).find(">:first-child");

		if ($expand.text() == "+") {
			$expand.text("-");
		} else {
			$expand.text("+");
		}
	});
});

function customExtender() {
	this.cfg.grid = {
		background : '#FFF' // Set background to white
	};
}

PrimeFaces.locales['es'] = {
	closeText : 'Cerrar',
	prevText : 'Anterior',
	nextText : 'Siguiente',
	monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
			'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
			'Diciembre' ],
	monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago',
			'Sep', 'Oct', 'Nov', 'Dic' ],
	dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
			'Viernes', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
	dayNamesMin : [ 'D', 'L', 'M', 'X', 'J', 'V', 'S' ],
	weekHeader : 'Semana',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Sólo hora',
	timeText : 'Tiempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Fecha actual',
	ampm : false,
	month : 'Mes',
	week : 'Semana',
	day : 'Día',
	allDayText : 'Todo el día'
};
