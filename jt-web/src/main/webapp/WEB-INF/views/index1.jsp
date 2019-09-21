<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0">
    <title>拆红包抽奖</title>
    <style type="text/css">
        *{
            margin:0;
            padding:0;
            list-style: none;
            border: 0;
        }
        @-webkit-keyframes shake {
            0% {
                -webkit-transform: rotate(2deg) translate3d(0,0,0)
            }

            50% {
                -webkit-transform: rotate(-2deg) translate3d(0,0,0)
            }

            100% {
                -webkit-transform: rotate(2deg) translate3d(0,0,0)
            }
        }

        @-moz-keyframes shake {
            0% {
                transform: rotate(2deg) translate3d(0,0,0)
            }

            50% {
                transform: rotate(-2deg) translate3d(0,0,0)
            }

            100% {
                transform: rotate(2deg) translate3d(0,0,0)
            }
        }

        @-ms-keyframes shake {
            0% {
                transform: rotate(2deg) translate3d(0,0,0)
            }

            50% {
                transform: rotate(-2deg) translate3d(0,0,0)
            }

            100% {
                transform: rotate(2deg) translate3d(0,0,0)
            }
        }

        .red{
            width: 300px;
            height: 345px;
            border-radius: 15px;
            box-shadow: 1px 1px 20px #666;
            position: fixed;
            top:50%;
            left: 50%;
            overflow: hidden;
            margin-left: -150px;
            margin-top: -172px;
            transform-origin: 50% 100%;
            -webkit-transform-origin: 50% 100%;
        }
        .red img{
            width:100%;
            height: auto;
        }
        .red.shake{
            animation:shake .2s infinite linear;
            -webkit-animation:shake .2s infinite linear;
        }
        .windows{
            width: 300px;
            height: 200px;
            position: absolute;
            top: 50%;
            margin-top: -100px;
            left: 50%;
            margin-left: -150px;
            border-radius: 15px;
            background: #c7c7c7;
            display: none;
            z-index: 11;
        }
        .text{
            text-align: center;
            font-size: 18px;
            font-family: "微软雅黑";
            vertical-align:middle;
            padding-top:60px;
        }
        .close{
            width: 30px;
            height: 30px;
            position: absolute;
            right: -10px;
            top: -10px;
            background: #c7c7c7;
            border-radius: 50%;
            cursor: pointer;
            text-align: center;
            transition:all 0.5s linear;
            -webkit-transition:all 0.5s linear;
            -moz-transition:all 0.5s linear;
            -ms-transition:all 0.5s linear;
            -o-transition:all 0.5s linear;
        }
        .close:hover{
            transform:rotate(180deg);
            -webkit-transform:rotate(180deg);
            -moz-transform:rotate(180deg);
            -ms-transform:rotate(180deg);
            -o-transform:rotate(180deg);
        }

        .close img{
            padding-top: 5px;

        }
        .opacity{
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom:0;
            background: #000;
            opacity:0.5;
            filter:alpha(opacity=50);
            z-index: 10;
            width: 100%;
            height: 100%;
            display: none;
        }
    </style>
    <script type="text/javascript" src="/js/JS/jquery-1.4.2.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".red").click(function(){
                $(this).addClass("shake");
                setTimeout(function(){
                    $(".red").removeClass("shake");
                    $(".windows").fadeIn();
                    $(".opacity").fadeIn();
                },2000);
            });
            $(".close").click(function(){
                $(this).parent().fadeOut();$(".opacity").fadeOut();
                $(".windows").css("display","none");
            })
        });
    </script>
</head>
<body>
<div class="opacity"></div>
<div class="red"><img src="/images/asd23.png"></div>
<div class="windows">
    <div class="text"><A href="#" >获得以下优惠券</div>
    <div class="text"><A href="#" >${coupon.name}</div>
    <div class="close"><img src="/images/close.png"/></div>
</div>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">

</div>
</body>
</html>