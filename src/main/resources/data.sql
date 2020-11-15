INSERT INTO GW_ROUTES (id,name,path,host,uri) VALUES (1,'GetRoute','/get',null,'http://httpbin.org:80')
INSERT INTO GW_ROUTES (id,name,path,host,uri) VALUES (2,'HystrixHost',null,'*.hystrix.com','http://httpbin.org:80')

INSERT INTO GW_ROUTES_HEADER(route_id,header,value) VALUES (1, 'Hello','World!')