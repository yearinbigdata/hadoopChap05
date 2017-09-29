

select count(*) from ontime;

desc ontime;

 
create index ontime_year_idx on ontime(year);

create index ontime_year_month_idx on ontime(year, month);

create index ontime_year_month_dayofmonth_idx on ontime(year, month, dayofmonth);

select year, count(*)
  from ontime
 group by year;
 
select year, month, count(*)
  from ontime
-- where year = 2008
 group by year, month
 union
select 'total', 'sum', count(*)
  from ontime;
  
-- 
-- 년도별 항공기 출발 지연 건수
--
select year, count(depdelay)
  from ontime
 where depdelay > 0
 group by year;
 
-- 
-- 월별 항공기 출발 지연 건수
--
select year, month, count(depdelay)
  from ontime
 where depdelay > 0
 group by year, month;







