<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Галерея изображений</title>
    <link href="/css/jquery.fancybox.css" rel="stylesheet" type="text/css"  media="screen" />
    <style>
        .thumb img {
            filter: none; /* IE6-9 */
            -webkit-filter: grayscale(0);
            border-radius:5px;
            background-color: #fff;
            border: 1px solid #ddd;
            padding:5px;
        }
        .thumb img:hover {
            /*  filter: gray; /* IE6-9 */
            -webkit-filter: grayscale(1);
        }
        .thumb {
            padding:5px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row" id="mainRow">
        <div class="col-md-3 col-sm-4 col-xs-6 thumb">
            <a class="fancyimage" rel="group" href="">
                <img class="img-responsive" src="" />
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("a.fancyimage").fancybox();
    });
</script>
</body>
</html>