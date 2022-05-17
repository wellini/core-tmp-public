alter table leaf alter column module_id drop not null;
alter table leaf alter column order_id drop not null;
alter table leaf drop column course_id;