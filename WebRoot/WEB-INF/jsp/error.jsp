<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/dashboard.do" var="homeURL" />
<script type="text/javascript">
    /* $("document").ready(function () {
        setTimeout(function () {
            if (window.opener && !window.opener.closed)
                window.close();
            else
                window.location = '${homeURL}';
        }, 10000);
    }); */
</script>