// 유효성 검사


jQuery(function (){ 
	jQuery('#save').click(function() { 
		var subject = jQuery('#form #subject').val(); 
		if (subject == '') { alert('제목을 입력하세요'); 
		return false; 
		}
		var subject = jQuery('#form #subject').val(); 
		if (subject == '[리뷰게시판]') { alert('제목을 입력하세요'); 
		return false; 
		}
		var subject = jQuery('#form #subject').val(); 
		if (subject == '[자유게시판]') { alert('제목을 입력하세요'); 
		return false; 
		}
		var subject = jQuery('#form #subject').val(); 
		if (subject == '[모임게시판]') { alert('제목을 입력하세요'); 
		return false; 
		}
		var subject = jQuery('#form #subject').val(); 
		if (subject == '[토론게시판]') { alert('제목을 입력하세요'); 
		return false; 
		}
		var subject = jQuery('#form #subject').val(); 
		if (subject == '[제태크노하우]') { alert('제목을 입력하세요'); 
		return false; 
		}
		var content = jQuery('#form #banks').val(); 
		if (content == '') { alert('은행명을 입력하세요'); 
		return false; 
		}
		var content = jQuery('#form #re_productname').val(); 
		if (content == '') { alert('상품명을 입력하세요'); 
		return false; 
		}
		var content = jQuery('#form #content').val(); 
		if (content == '') { alert('내용을 입력하세요'); 
		return false; 
		}
		
		jQuery('#save').submit(); 
	}); 
		
	jQuery('#search').click(function() { 
		var searchStr = jQuery('#searchf #searchStr').val(); 
		if (searchStr == '') { alert('검색어를 입력하세요'); 
		return false; 
		}
		jQuery('#find').submit(); 
		}); 
		
		
	}); 