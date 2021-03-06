'use strict'






       $(document).ready(function(){

               //댓글보기
               $.ajax({
                type : "POST",
                contentType: "application/json",
                data : listcomment,
                url : "/comment",
                success : function(res){
                    console.log(res.comments);
                    $('.comment').html(temHtml(res.comments));
                    showpaging(listcomment);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
            }); //ajax 끝



            //댓글 삭제버튼 누르면 삭제
            $('.comment').on('click','.btndel',function(){

                if(!confirm("정말 삭제하시겠습니까?")) return;

                let cno = $(this).parent().attr('data_cno');
                let bno = $(this).parent().attr('data_bno');

                let content = JSON.stringify({
                    "commentnumber":cno,
                    "boardnumber":bno
                })

                console.log(content);

                $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "/commentdelete",
                data : content,
                success : function(res){
                    showcomment(listcomment);
                    showpaging(listcomment);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
            });//ajax 끝

            });//click끝


            //댓글 쓰기
             $('.btnwrite').click(function(){

                let commentcontent = $('.commenttt').val();

                console.log(commentcontent);


                let content = JSON.stringify({
                    "commentcontent":commentcontent,
                    "boardnumber":10,
                    "usernumber":10,
                    "nickname":"귀여운게죄라면난사형"
                })

                $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "/commentwrite",
                data : content,
                success : function(res){
                    showcomment(listcomment);
                    showpaging(listcomment);

                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
            });//ajax 끝

            });//click끝



            //댓글 update하기
             $('.comment').on('click','.btnupd',function(){

                let cno = $(this).parent().attr('data_cno');
                let bno = $(this).parent().attr('data_bno');

                let origincontent = $(this).prev().prev().children().
                children('.commentcontent').children('#targetcontent').text();

                $('#btn-write-modify').attr('data_cno',cno);

                if($('.modal').css('display') == 'none'){
                    $('.modal').css('display','block');
                    $('.modify-writebox-content').children().val(origincontent);

                } //if문 끝

            });//click끝


            //댓글 update보내기
             $('#btn-write-modify').click(function(){

                if(!confirm('정말 수정하시겠습니까?')) return;

                let updatecontent = $('.modify-writebox-content').children().val();
                let cno = $('#btn-write-modify').attr('data_cno');

                let content = JSON.stringify({
                    "commentnumber":cno,
                    "commentcontent":updatecontent
                })

                $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "/commentupdate",
                data : content,
                success : function(res){
                    showcomment(listcomment);
                    showpaging(listcomment);
                    $('.modal').css('display','none');

                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }

            });//ajax 끝


            });//click끝

                $('.close').click(function(){

                    if($('.modal').css('display') == 'block'){
                    $('.modal').css('display','none');

                } //if문 끝

                })


                //답글 입력창보여주기.
                 $('.comment').on('click','.btnreply',function(){

                    console.log('ok');

                    $("#replyform").appendTo($(this).parent());

                    $('#replyform').css('display','block');

                 })

                 //답글 쓰기
                 $('#wrtrepbtn').click(function(){

                     let pcno = $(this).parent().parent().attr('data_pcno');
                     let targetnickname = $(this).parent().parent().attr('data_nickname');
                     let replycontent = $('input[name=replycomment]').val()

                     console.info(targetnickname)

                     if(replycontent.trim() == ''){
                        alert('내용을 입력해주세요.')
                        $('input[name=replycomment]').focus();
                        return;
                     }

                     let reply = JSON.stringify({
                        "commentcontent":replycontent,
                        "usernumber":10,
                        "parentcommentnumber":pcno,
                        "nickname":"귀여운게죄라면난사형",
                        "boardnumber":10,
                        "targetnickname":targetnickname
                     })

                     console.info(reply)

                    $.ajax({
                    type : "POST",
                    contentType: "application/json",
                    data:reply,
                    url : "/replyinsert",
                    success : function(res){
                        alert('성공적으로 완료하였습니다.');
                        showcomment(listcomment);
                        showpaging(listcomment);
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
                }); //ajax 끝

                $('#replyform').css('display','none');
                $('input[name=replycomment]').val('');
                $('#replyform').appendTo('body');

                }) //답글쓰기끗

            }); //ready끝




            let temHtml = function(comments){
            let tmp = '<ul class="comment_ul">';

            comments.forEach(function(comment){
                    tmp += ' <li data_cno='+comment.commentnumber
                    tmp += ' data_pcno ='+comment.parentcommentnumber
                    tmp += ' data_bno='+comment.boardnumber
                    tmp += ' data_nickname='+comment.nickname
                    tmp += ' data_uno='+comment.usernumber + '>'
                    tmp += ' <div class="comment_div"> '
                    tmp += ' <div class="user_info"> '
                    if(comment.commentnumber != comment.parentcommentnumber)
                   {tmp += ' ㄴ'}
                    tmp += ' <span data_ninkname='+comment.nickname
                    tmp += ' data_date='+comment.commentdate +'>'
                    tmp +=   comment.nickname + ' ' + comment.commentdate + ' 수정 삭제 답글'
                    tmp +=   '</span>'
                    tmp +=   '<div class="commentcontent">'
                    if(comment.commentnumber != comment.parentcommentnumber)
                    {tmp +=  '<span id="targetnickname">@'+comment.targetnickname+'</span>&nbsp;&nbsp;'}
                    tmp +=   '<span id="targetcontent">'+comment.commentcontent+'</span>'
                    tmp +=   '</div>'
                    tmp +=   '</div>'
                    tmp +=   '</div>'
                    tmp += ' <button class="btndel">삭제</button>'
                    tmp += ' <button class="btnupd">수정</button>'
                    tmp += ' <button class="btnreply">답글</button>'
                    tmp += ' </li>'
                    tmp += ' <hr class="hr">'

                    console.log(comment);

                });

                return tmp + "</ul>";
            } //함수끝


            let showcomment = function(listcomment){

            $.ajax({
                type : "POST",
                contentType: "application/json",
                url : "/comment",
                data : listcomment,
                success : function(res){
                    $('.comment').html(temHtml(res.comments));
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
            }); //ajax 끝

            }// 함수끝

            //paging함수
            let showpaging = function(listcomment){
               $.ajax({
                type : "POST",
                contentType: "application/json",
                data:listcomment,
                url : "/test2",
                success : function(res){
                    $('#commentpaging').html(commentpaginghtml(res.paging));
                },
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    console.log(textStatus);
                    console.log(errorThrown);
                    alert("오류가 발생했습니다.")
                }
            }); //ajax 끝
        } //함수끝


        //paging rendering 함수
        let commentpaginghtml = function(paging){

    let a = '';

    console.log(paging.showPrev);

    if(paging.showPrev){
            a += '<a href="/"> PREV </a>  '
        }

    for(let num=paging.beginPage; num<=paging.endPage; num++){


        a += '<a href="/test?cpage='+num+'&cpagesize='+paging.pageSize+'&boardnumber=10#hello'+'">'+num+'</a>  '

    }

    console.log(paging.showNext);

    if(paging.showNext){
            a += '<a href="/"> NEXT </a>  '
        }

    return a;

    } //paging rendering 함수
