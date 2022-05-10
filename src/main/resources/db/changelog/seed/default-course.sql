insert into course (
    id,
    title,
    author_id,
    cover_theme,
    cover_url
) values (
             '5685cac5-4b42-48ba-a337-21d885faf4e1',
             'Матемастический анализ I семестр',
             '3950aca1-63d4-4573-a452-d00104e5e7fd',
             'BLACK',
             ''
         );

insert into module (id,                                     title,                     order_id, course_id)
values             ('4645fafe-7939-41b9-a5fe-9f9d92c5f86c', 'Аналитическая геометрия', 1,        '5685cac5-4b42-48ba-a337-21d885faf4e1');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('2737f14e-1917-4954-b922-1161e253aa5d', 'TEXT', 'Векторы', 1, '4645fafe-7939-41b9-a5fe-9f9d92c5f86c', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('2737f14e-1917-4954-b922-1161e253aa5d', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('280bdf15-f379-4571-b71f-f04c9fad1d7c', 'TEXT', 'Линейная (не) зависимость векторов. Базис векторов', 2, '4645fafe-7939-41b9-a5fe-9f9d92c5f86c', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('280bdf15-f379-4571-b71f-f04c9fad1d7c', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('c6ea6eeb-96e3-4b7d-9448-55ac04158928', 'TEXT', 'Прямая на плоскости', 3, '4645fafe-7939-41b9-a5fe-9f9d92c5f86c', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('c6ea6eeb-96e3-4b7d-9448-55ac04158928', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('c98e43f6-ea72-4767-a7dd-081b73ed3cd1', 'TEXT', 'Формулы деления отрезка в данном отношении', 4, '4645fafe-7939-41b9-a5fe-9f9d92c5f86c', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('c98e43f6-ea72-4767-a7dd-081b73ed3cd1', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('8b267ebe-1f05-4c41-bf79-0dced4d0066f', 'TEXT', 'Векторное и смешанное произведение векторов', 5, '4645fafe-7939-41b9-a5fe-9f9d92c5f86c', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('8b267ebe-1f05-4c41-bf79-0dced4d0066f', '');

insert into module (id,                                     title,                     order_id, course_id)
values             ('4fedd700-9a32-4b0a-a617-1d1c5eacb584', 'Элементы высшей алгебры', 2,        '5685cac5-4b42-48ba-a337-21d885faf4e1');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('922fb803-5cd2-4e7a-861e-0584c8d4f2ad', 'TEXT', 'Множества и действия над ними', 1, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('922fb803-5cd2-4e7a-861e-0584c8d4f2ad', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('086836ac-1479-49a9-b5f2-60e4ee66b703', 'TEXT', 'Основы математической логики', 2, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('086836ac-1479-49a9-b5f2-60e4ee66b703', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('c5458cb8-32f1-4d37-b682-ae8ed8f6c2e6', 'TEXT', 'Формулы и законы логики', 3, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('c5458cb8-32f1-4d37-b682-ae8ed8f6c2e6', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('4ae53714-2210-4a85-827d-772792f3a832', 'TEXT', 'Уравнения высшей математики', 4, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('4ae53714-2210-4a85-827d-772792f3a832', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('76c6c828-ce61-41d4-8a75-f18580a49724', 'TEXT', 'Комплексные числа', 5, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('76c6c828-ce61-41d4-8a75-f18580a49724', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('44e2d1e8-2e05-4c2f-be99-d004444b6af2', 'TEXT', 'Выражения, уравнения и с-мы с комплексными числами', 6, '4fedd700-9a32-4b0a-a617-1d1c5eacb584', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('44e2d1e8-2e05-4c2f-be99-d004444b6af2', '');

insert into module (id,                                     title,                     order_id, course_id)
values             ('21cf7b29-4369-42a7-b919-5a2097d72c97', 'Пределы',                 3,        '5685cac5-4b42-48ba-a337-21d885faf4e1');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('610e7c0e-37d5-45b7-9b68-21c2e7886365', 'TEXT', 'Множества и действия над ними', 1, '21cf7b29-4369-42a7-b919-5a2097d72c97', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('610e7c0e-37d5-45b7-9b68-21c2e7886365', '');

insert into leaf (id, type, title, order_id, module_id, course_id)
values           ('a3a52f39-564b-4247-b364-a879b4afd5f6', 'TEXT', 'Основы математической логики', 2, '21cf7b29-4369-42a7-b919-5a2097d72c97', '5685cac5-4b42-48ba-a337-21d885faf4e1');
insert into leaf_text (id, text) values ('a3a52f39-564b-4247-b364-a879b4afd5f6', '');