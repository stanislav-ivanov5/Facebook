$(document).ready(function() {
   /*============ Chat sidebar ========*/
  $('.chat-sidebar, .nav-controller, .chat-sidebar a').on('click', function(event) {
      $('.chat-sidebar').toggleClass('focus');
  });

  $(".hide-chat").click(function(){
      $('.chat-sidebar').toggleClass('focus');
  });

  /*============= About page ==============*/
  $(".about-tab-menu .list-group-item").click(function(e) {
      e.preventDefault();
      $(this).siblings('a.active').removeClass("active");
      $(this).addClass("active");
      var index = $(this).index();
      $("div.about-tab>div.about-tab-content").removeClass("active");
      $("div.about-tab>div.about-tab-content").eq(index).addClass("active");
  });
  /*==============  show panel ===============*/
  $(".btn-frm").click(function(){
    $(".frm").toggleClass("hidden");
    $(".frm").toggleClass("animated");
    $(".frm").toggleClass("flipInX");
    $(".users-row").addClass('fadeInDown');
  });

  /*==============  Messages ===============*/
  if ($('#ms-menu-trigger')[0]) {
    $('body').on('click', '#ms-menu-trigger', function() {
        $('.ms-menu').toggleClass('toggled'); 
    });
  }
})