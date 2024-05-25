PGDMP  -                    |            vet-project    13.14    16.2 I    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    70077    vet-project    DATABASE     �   CREATE DATABASE "vet-project" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE "vet-project";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false                       0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    70989    animals    TABLE     �  CREATE TABLE public.animals (
    animal_id integer NOT NULL,
    animal_birthday date NOT NULL,
    animal_breed character varying(255) NOT NULL,
    animal_color character varying(255) NOT NULL,
    animal_gender character varying(255) NOT NULL,
    animal_name character varying(255) NOT NULL,
    animal_species character varying(255) NOT NULL,
    animal_customer_id integer NOT NULL
);
    DROP TABLE public.animals;
       public         heap    postgres    false    4            �            1259    70987    animals_animal_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.animals_animal_customer_id_seq;
       public          postgres    false    4    202                       0    0    animals_animal_customer_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.animals_animal_customer_id_seq OWNED BY public.animals.animal_customer_id;
          public          postgres    false    201            �            1259    70985    animals_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.animals_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.animals_animal_id_seq;
       public          postgres    false    4    202                       0    0    animals_animal_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.animals_animal_id_seq OWNED BY public.animals.animal_id;
          public          postgres    false    200            �            1259    71005    appointments    TABLE     �   CREATE TABLE public.appointments (
    appointment_id integer NOT NULL,
    appointment_date timestamp(6) without time zone NOT NULL,
    appointment_animal_id integer NOT NULL,
    appointment_doctor_id integer NOT NULL
);
     DROP TABLE public.appointments;
       public         heap    postgres    false    4            �            1259    71001 &   appointments_appointment_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_animal_id_seq;
       public          postgres    false    206    4                       0    0 &   appointments_appointment_animal_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_animal_id_seq OWNED BY public.appointments.appointment_animal_id;
          public          postgres    false    204            �            1259    71003 &   appointments_appointment_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.appointments_appointment_doctor_id_seq;
       public          postgres    false    4    206                       0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.appointments_appointment_doctor_id_seq OWNED BY public.appointments.appointment_doctor_id;
          public          postgres    false    205            �            1259    70999    appointments_appointment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appointments_appointment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.appointments_appointment_id_seq;
       public          postgres    false    206    4                       0    0    appointments_appointment_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.appointments_appointment_id_seq OWNED BY public.appointments.appointment_id;
          public          postgres    false    203            �            1259    71017    available_dates    TABLE     �   CREATE TABLE public.available_dates (
    available_date_id integer NOT NULL,
    available_date_date date NOT NULL,
    available_date_doctor_id integer NOT NULL
);
 #   DROP TABLE public.available_dates;
       public         heap    postgres    false    4            �            1259    71015 ,   available_dates_available_date_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_available_date_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.available_dates_available_date_doctor_id_seq;
       public          postgres    false    209    4            	           0    0 ,   available_dates_available_date_doctor_id_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.available_dates_available_date_doctor_id_seq OWNED BY public.available_dates.available_date_doctor_id;
          public          postgres    false    208            �            1259    71013 %   available_dates_available_date_id_seq    SEQUENCE     �   CREATE SEQUENCE public.available_dates_available_date_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.available_dates_available_date_id_seq;
       public          postgres    false    209    4            
           0    0 %   available_dates_available_date_id_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.available_dates_available_date_id_seq OWNED BY public.available_dates.available_date_id;
          public          postgres    false    207            �            1259    71026 	   customers    TABLE     G  CREATE TABLE public.customers (
    customer_id integer NOT NULL,
    customer_address character varying(255) NOT NULL,
    customer_city character varying(255) NOT NULL,
    customer_mail character varying(255) NOT NULL,
    customer_name character varying(255) NOT NULL,
    customer_phone character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false    4            �            1259    71024    customers_customer_id_seq    SEQUENCE     �   CREATE SEQUENCE public.customers_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.customers_customer_id_seq;
       public          postgres    false    4    211                       0    0    customers_customer_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;
          public          postgres    false    210            �            1259    71037    doctors    TABLE       CREATE TABLE public.doctors (
    doctor_id integer NOT NULL,
    doctor_address character varying(255),
    doctor_city character varying(255),
    doctor_mail character varying(255),
    doctor_name character varying(255),
    doctor_phone character varying(255)
);
    DROP TABLE public.doctors;
       public         heap    postgres    false    4            �            1259    71035    doctors_doctor_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doctors_doctor_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.doctors_doctor_id_seq;
       public          postgres    false    4    213                       0    0    doctors_doctor_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.doctors_doctor_id_seq OWNED BY public.doctors.doctor_id;
          public          postgres    false    212            �            1259    71050    vaccines    TABLE     
  CREATE TABLE public.vaccines (
    vaccine_id integer NOT NULL,
    vaccine_code character varying(255),
    vaccine_name character varying(255),
    vaccine_protection_fnsh_date date,
    vaccine_protection_strt_date date,
    vaccine_animal_id integer NOT NULL
);
    DROP TABLE public.vaccines;
       public         heap    postgres    false    4            �            1259    71048    vaccines_vaccine_animal_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_animal_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.vaccines_vaccine_animal_id_seq;
       public          postgres    false    216    4                       0    0    vaccines_vaccine_animal_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.vaccines_vaccine_animal_id_seq OWNED BY public.vaccines.vaccine_animal_id;
          public          postgres    false    215            �            1259    71046    vaccines_vaccine_id_seq    SEQUENCE     �   CREATE SEQUENCE public.vaccines_vaccine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.vaccines_vaccine_id_seq;
       public          postgres    false    216    4                       0    0    vaccines_vaccine_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.vaccines_vaccine_id_seq OWNED BY public.vaccines.vaccine_id;
          public          postgres    false    214            N           2604    70992    animals animal_id    DEFAULT     v   ALTER TABLE ONLY public.animals ALTER COLUMN animal_id SET DEFAULT nextval('public.animals_animal_id_seq'::regclass);
 @   ALTER TABLE public.animals ALTER COLUMN animal_id DROP DEFAULT;
       public          postgres    false    200    202    202            O           2604    70993    animals animal_customer_id    DEFAULT     �   ALTER TABLE ONLY public.animals ALTER COLUMN animal_customer_id SET DEFAULT nextval('public.animals_animal_customer_id_seq'::regclass);
 I   ALTER TABLE public.animals ALTER COLUMN animal_customer_id DROP DEFAULT;
       public          postgres    false    201    202    202            P           2604    71008    appointments appointment_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_id SET DEFAULT nextval('public.appointments_appointment_id_seq'::regclass);
 J   ALTER TABLE public.appointments ALTER COLUMN appointment_id DROP DEFAULT;
       public          postgres    false    203    206    206            Q           2604    71009 "   appointments appointment_animal_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_animal_id SET DEFAULT nextval('public.appointments_appointment_animal_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_animal_id DROP DEFAULT;
       public          postgres    false    206    204    206            R           2604    71010 "   appointments appointment_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.appointments ALTER COLUMN appointment_doctor_id SET DEFAULT nextval('public.appointments_appointment_doctor_id_seq'::regclass);
 Q   ALTER TABLE public.appointments ALTER COLUMN appointment_doctor_id DROP DEFAULT;
       public          postgres    false    205    206    206            S           2604    71020 !   available_dates available_date_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN available_date_id SET DEFAULT nextval('public.available_dates_available_date_id_seq'::regclass);
 P   ALTER TABLE public.available_dates ALTER COLUMN available_date_id DROP DEFAULT;
       public          postgres    false    207    209    209            T           2604    71021 (   available_dates available_date_doctor_id    DEFAULT     �   ALTER TABLE ONLY public.available_dates ALTER COLUMN available_date_doctor_id SET DEFAULT nextval('public.available_dates_available_date_doctor_id_seq'::regclass);
 W   ALTER TABLE public.available_dates ALTER COLUMN available_date_doctor_id DROP DEFAULT;
       public          postgres    false    208    209    209            U           2604    71029    customers customer_id    DEFAULT     ~   ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);
 D   ALTER TABLE public.customers ALTER COLUMN customer_id DROP DEFAULT;
       public          postgres    false    210    211    211            V           2604    71040    doctors doctor_id    DEFAULT     v   ALTER TABLE ONLY public.doctors ALTER COLUMN doctor_id SET DEFAULT nextval('public.doctors_doctor_id_seq'::regclass);
 @   ALTER TABLE public.doctors ALTER COLUMN doctor_id DROP DEFAULT;
       public          postgres    false    212    213    213            W           2604    71053    vaccines vaccine_id    DEFAULT     z   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_id SET DEFAULT nextval('public.vaccines_vaccine_id_seq'::regclass);
 B   ALTER TABLE public.vaccines ALTER COLUMN vaccine_id DROP DEFAULT;
       public          postgres    false    214    216    216            X           2604    71054    vaccines vaccine_animal_id    DEFAULT     �   ALTER TABLE ONLY public.vaccines ALTER COLUMN vaccine_animal_id SET DEFAULT nextval('public.vaccines_vaccine_animal_id_seq'::regclass);
 I   ALTER TABLE public.vaccines ALTER COLUMN vaccine_animal_id DROP DEFAULT;
       public          postgres    false    216    215    216            �          0    70989    animals 
   TABLE DATA           �   COPY public.animals (animal_id, animal_birthday, animal_breed, animal_color, animal_gender, animal_name, animal_species, animal_customer_id) FROM stdin;
    public          postgres    false    202   �[       �          0    71005    appointments 
   TABLE DATA           v   COPY public.appointments (appointment_id, appointment_date, appointment_animal_id, appointment_doctor_id) FROM stdin;
    public          postgres    false    206   �\       �          0    71017    available_dates 
   TABLE DATA           k   COPY public.available_dates (available_date_id, available_date_date, available_date_doctor_id) FROM stdin;
    public          postgres    false    209   q]       �          0    71026 	   customers 
   TABLE DATA              COPY public.customers (customer_id, customer_address, customer_city, customer_mail, customer_name, customer_phone) FROM stdin;
    public          postgres    false    211   �]       �          0    71037    doctors 
   TABLE DATA           q   COPY public.doctors (doctor_id, doctor_address, doctor_city, doctor_mail, doctor_name, doctor_phone) FROM stdin;
    public          postgres    false    213   �_       �          0    71050    vaccines 
   TABLE DATA           �   COPY public.vaccines (vaccine_id, vaccine_code, vaccine_name, vaccine_protection_fnsh_date, vaccine_protection_strt_date, vaccine_animal_id) FROM stdin;
    public          postgres    false    216   �`                  0    0    animals_animal_customer_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.animals_animal_customer_id_seq', 1, false);
          public          postgres    false    201                       0    0    animals_animal_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.animals_animal_id_seq', 8, true);
          public          postgres    false    200                       0    0 &   appointments_appointment_animal_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_animal_id_seq', 1, false);
          public          postgres    false    204                       0    0 &   appointments_appointment_doctor_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public.appointments_appointment_doctor_id_seq', 1, false);
          public          postgres    false    205                       0    0    appointments_appointment_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.appointments_appointment_id_seq', 7, true);
          public          postgres    false    203                       0    0 ,   available_dates_available_date_doctor_id_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public.available_dates_available_date_doctor_id_seq', 1, false);
          public          postgres    false    208                       0    0 %   available_dates_available_date_id_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.available_dates_available_date_id_seq', 6, true);
          public          postgres    false    207                       0    0    customers_customer_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customers_customer_id_seq', 8, true);
          public          postgres    false    210                       0    0    doctors_doctor_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doctors_doctor_id_seq', 6, true);
          public          postgres    false    212                       0    0    vaccines_vaccine_animal_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.vaccines_vaccine_animal_id_seq', 1, false);
          public          postgres    false    215                       0    0    vaccines_vaccine_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.vaccines_vaccine_id_seq', 6, true);
          public          postgres    false    214            Z           2606    70998    animals animals_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (animal_id);
 >   ALTER TABLE ONLY public.animals DROP CONSTRAINT animals_pkey;
       public            postgres    false    202            \           2606    71012    appointments appointments_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT appointments_pkey PRIMARY KEY (appointment_id);
 H   ALTER TABLE ONLY public.appointments DROP CONSTRAINT appointments_pkey;
       public            postgres    false    206            ^           2606    71023 $   available_dates available_dates_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT available_dates_pkey PRIMARY KEY (available_date_id);
 N   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT available_dates_pkey;
       public            postgres    false    209            `           2606    71034    customers customers_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    211            b           2606    71045    doctors doctors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (doctor_id);
 >   ALTER TABLE ONLY public.doctors DROP CONSTRAINT doctors_pkey;
       public            postgres    false    213            d           2606    71059    vaccines vaccines_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT vaccines_pkey PRIMARY KEY (vaccine_id);
 @   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT vaccines_pkey;
       public            postgres    false    216            f           2606    71070 '   appointments fk27d0xcbajwaeeun2doojsae4    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fk27d0xcbajwaeeun2doojsae4 FOREIGN KEY (appointment_doctor_id) REFERENCES public.doctors(doctor_id);
 Q   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fk27d0xcbajwaeeun2doojsae4;
       public          postgres    false    213    2914    206            i           2606    71080 $   vaccines fkekhfjmpsduds8nnilqe9b467v    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccines
    ADD CONSTRAINT fkekhfjmpsduds8nnilqe9b467v FOREIGN KEY (vaccine_animal_id) REFERENCES public.animals(animal_id);
 N   ALTER TABLE ONLY public.vaccines DROP CONSTRAINT fkekhfjmpsduds8nnilqe9b467v;
       public          postgres    false    202    2906    216            h           2606    71075 *   available_dates fkf7jtwolyhaj07c4oh0j4pncb    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_dates
    ADD CONSTRAINT fkf7jtwolyhaj07c4oh0j4pncb FOREIGN KEY (available_date_doctor_id) REFERENCES public.doctors(doctor_id);
 T   ALTER TABLE ONLY public.available_dates DROP CONSTRAINT fkf7jtwolyhaj07c4oh0j4pncb;
       public          postgres    false    209    2914    213            e           2606    71060 #   animals fknjsvd8kplxqmf48ybxayrx6ru    FK CONSTRAINT     �   ALTER TABLE ONLY public.animals
    ADD CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru FOREIGN KEY (animal_customer_id) REFERENCES public.customers(customer_id);
 M   ALTER TABLE ONLY public.animals DROP CONSTRAINT fknjsvd8kplxqmf48ybxayrx6ru;
       public          postgres    false    202    211    2912            g           2606    71065 (   appointments fko4t945rb708af9ry6syof7bwt    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointments
    ADD CONSTRAINT fko4t945rb708af9ry6syof7bwt FOREIGN KEY (appointment_animal_id) REFERENCES public.animals(animal_id);
 R   ALTER TABLE ONLY public.appointments DROP CONSTRAINT fko4t945rb708af9ry6syof7bwt;
       public          postgres    false    202    2906    206            �   �   x�}��N�@�g�]��KҖ�A*��3����S{�⻀���Ll�ť� �����ɿsP�R��Z(D��rT�%�X
8��O|��GzM�$�Ϸ�+,&�� %� Ƶ�J��/Q��a�4���fز�#C-��P���ߎ�3ڙ��q��o�&��h�p9�$}+da��p+�v��z�	����K\E,�NX�����N��[���jc�D�p=�:��e(e{�@�#�SӘ k|�B�/��wE      �   d   x�m˻1ј���]�ԧ�_�O�a`����ͳ���6hS �~k�渻��}'-�
5Λ_�Q����͇Σ1̃��ZG럖����}����4�      �   4   x�3�4202�50"NC.# �� �5�2A�5�2E�r���fP�W� D�      �   �  x�m�M��0��ί�c��ȟqr�cWUQ�]-����71Mld����P�E�%����}g�!(Dol{�L�A��غ�>�Q��l��U��(eUg쿂�z������/㨮$��1:�B��.4�Q�o��&��c��|��*����0M��m�:g�nK5��M����gNq.'gLE&�`5|����Oj��!�(n��'Ag�;�	<��s�+�R>�ᎱCm���Sﶝ�U{4��W�Y��0۩!�/$'B䤢y)(;3A��J$������ۛq-Hc�uxP�����D��N�^�w�Oh�v#��>hea��CaI�R�V�$¬��D/��I����u���0��W�d]1/����h�:���W�qڹ��i������Q�e^c�K��s���
q.Y	sobTv��;���ٰ����Z L��S'�r-����h��w�?���$���J�Q���ē�,k�,�~����      �   N  x�UQKN�0]ۧ�=QD>NZVM��Te�
�����$��W V�5�vTua�g<~o�sD8��k�J�+5�Q��4&�J[D��)t��"��U8{W8v9aiG4!�OݔB��nBx���A0�����w�~m%��p�x�R*�������_�s��r3��D�f�������x0��D�w���� �ߨZ�p� ښ��JsMeO{��~&J#�NrȜ4�ȢU�֔֍��mO�@1p�,ќ�}gQ���cP|жF}Ŷ��$=���On�,��{����x��8���F��55���������/B!�ЌA�1>�%���֧      �   �   x�u��
�0ϻ���d�Mr�PJ�������/Brzo��88�`�3e,)!�?��u VN�T�{)i����������`��+a��.�	�8�KEl`�v$�W�8�X+
j(kN����)o4j����6�     