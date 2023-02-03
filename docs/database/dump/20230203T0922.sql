--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: topbid(integer); Type: FUNCTION; Schema: public; Owner: enchere
--

CREATE FUNCTION public.topbid(auction integer) RETURNS TABLE(id integer, amount real, date timestamp with time zone, app_user_id integer, auction_id integer)
    LANGUAGE plpgsql
    AS $$
    begin
        return query select * from bid_history bh where bh.auction_id=auction order by date desc limit 1;
    end;
    $$;


ALTER FUNCTION public.topbid(auction integer) OWNER TO enchere;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.admin (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(20) NOT NULL,
    password character varying(20) NOT NULL
);


ALTER TABLE public.admin OWNER TO enchere;

--
-- Name: admin_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_id_seq OWNER TO enchere;

--
-- Name: admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.admin_id_seq OWNED BY public.admin.id;


--
-- Name: admin_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_seq OWNER TO enchere;

--
-- Name: admin_token; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.admin_token (
    id integer NOT NULL,
    value character varying(255) NOT NULL,
    expiration_date timestamp without time zone NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    adminid integer NOT NULL
);


ALTER TABLE public.admin_token OWNER TO enchere;

--
-- Name: admin_token_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_token_id_seq OWNER TO enchere;

--
-- Name: admin_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.admin_token_id_seq OWNED BY public.admin_token.id;


--
-- Name: admin_token_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.admin_token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_token_seq OWNER TO enchere;

--
-- Name: app_user; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(20) NOT NULL,
    account_balance real DEFAULT 0 NOT NULL,
    birth_date date NOT NULL,
    gender_id integer NOT NULL,
    register_date date NOT NULL
);


ALTER TABLE public.app_user OWNER TO enchere;

--
-- Name: app_user_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_id_seq OWNER TO enchere;

--
-- Name: app_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;


--
-- Name: reload_request; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_request (
    id integer NOT NULL,
    date timestamp with time zone DEFAULT now() NOT NULL,
    amount real NOT NULL,
    app_user_id integer NOT NULL
);


ALTER TABLE public.reload_request OWNER TO enchere;

--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_request_id_seq OWNER TO enchere;

--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_recharge_request_id_seq OWNED BY public.reload_request.id;


--
-- Name: app_user_recharge_request_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_request_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_request_seq OWNER TO enchere;

--
-- Name: reload_request_state_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_request_state_history (
    id integer NOT NULL,
    date timestamp with time zone DEFAULT now(),
    reload_request_id integer NOT NULL,
    reload_state_id integer NOT NULL
);


ALTER TABLE public.reload_request_state_history OWNER TO enchere;

--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_state_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_state_history_id_seq OWNER TO enchere;

--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_recharge_state_history_id_seq OWNED BY public.reload_request_state_history.id;


--
-- Name: app_user_recharge_state_history_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_recharge_state_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_recharge_state_history_seq OWNER TO enchere;

--
-- Name: app_user_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_seq OWNER TO enchere;

--
-- Name: app_user_token; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.app_user_token (
    id integer NOT NULL,
    value character varying(255) NOT NULL,
    expiration_date timestamp without time zone NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    app_userid integer NOT NULL,
    app_userdescription character varying(50) NOT NULL
);


ALTER TABLE public.app_user_token OWNER TO enchere;

--
-- Name: app_user_token_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_token_id_seq OWNER TO enchere;

--
-- Name: app_user_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.app_user_token_id_seq OWNED BY public.app_user_token.id;


--
-- Name: app_user_token_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.app_user_token_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_user_token_seq OWNER TO enchere;

--
-- Name: auction; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.auction (
    id integer NOT NULL,
    description text NOT NULL,
    app_user_id integer NOT NULL,
    category_id integer NOT NULL,
    start_date timestamp with time zone NOT NULL,
    end_date timestamp with time zone NOT NULL,
    starting_price real NOT NULL,
    bid_step real NOT NULL,
    title character varying(50) NOT NULL,
    commission_rate_id integer,
    app_userid bigint,
    auction_stateid bigint
);


ALTER TABLE public.auction OWNER TO enchere;

--
-- Name: auction_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_id_seq OWNER TO enchere;

--
-- Name: auction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.auction_id_seq OWNED BY public.auction.id;


--
-- Name: auction_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_seq OWNER TO enchere;

--
-- Name: auction_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.auction_state (
    id integer NOT NULL,
    value character varying(25) NOT NULL
);


ALTER TABLE public.auction_state OWNER TO enchere;

--
-- Name: auction_state_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_state_id_seq OWNER TO enchere;

--
-- Name: auction_state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.auction_state_id_seq OWNED BY public.auction_state.id;


--
-- Name: auction_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.auction_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_state_seq OWNER TO enchere;

--
-- Name: bid_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.bid_history (
    id integer NOT NULL,
    amount real NOT NULL,
    date timestamp with time zone NOT NULL,
    app_user_id integer NOT NULL,
    auction_id integer NOT NULL
);


ALTER TABLE public.bid_history OWNER TO enchere;

--
-- Name: bid_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_history_id_seq OWNER TO enchere;

--
-- Name: bid_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.bid_history_id_seq OWNED BY public.bid_history.id;


--
-- Name: bid_history_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_history_seq OWNER TO enchere;

--
-- Name: bid_photos; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.bid_photos (
    id integer NOT NULL,
    bid_id integer NOT NULL,
    picture bytea NOT NULL
);


ALTER TABLE public.bid_photos OWNER TO enchere;

--
-- Name: bid_photos_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.bid_photos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bid_photos_id_seq OWNER TO enchere;

--
-- Name: bid_photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.bid_photos_id_seq OWNED BY public.bid_photos.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.category OWNER TO enchere;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO enchere;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: category_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.category_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_seq OWNER TO enchere;

--
-- Name: daily_auction; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.daily_auction (
    id bigint NOT NULL,
    amount real,
    date timestamp(6) without time zone
);


ALTER TABLE public.daily_auction OWNER TO enchere;

--
-- Name: daily_auction_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_auction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_auction_id_seq OWNER TO enchere;

--
-- Name: daily_auction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.daily_auction_id_seq OWNED BY public.daily_auction.id;


--
-- Name: daily_auction_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_auction_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_auction_seq OWNER TO enchere;

--
-- Name: daily_sales; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.daily_sales (
    id bigint NOT NULL,
    amount real,
    date timestamp(6) without time zone
);


ALTER TABLE public.daily_sales OWNER TO enchere;

--
-- Name: daily_sales_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.daily_sales_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.daily_sales_id_seq OWNER TO enchere;

--
-- Name: daily_sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.daily_sales_id_seq OWNED BY public.daily_sales.id;


--
-- Name: genrder; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.genrder (
    id integer NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.genrder OWNER TO enchere;

--
-- Name: genrder_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.genrder_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genrder_id_seq OWNER TO enchere;

--
-- Name: genrder_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.genrder_id_seq OWNED BY public.genrder.id;


--
-- Name: reload_state; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.reload_state (
    id integer NOT NULL,
    description character varying(50) NOT NULL
);


ALTER TABLE public.reload_state OWNER TO enchere;

--
-- Name: recharge_state_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.recharge_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recharge_state_id_seq OWNER TO enchere;

--
-- Name: recharge_state_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.recharge_state_id_seq OWNED BY public.reload_state.id;


--
-- Name: recharge_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.recharge_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recharge_state_seq OWNER TO enchere;

--
-- Name: reload_request_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_request_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_request_seq OWNER TO enchere;

--
-- Name: reload_request_state_history_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_request_state_history_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_request_state_history_seq OWNER TO enchere;

--
-- Name: reload_state_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.reload_state_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reload_state_seq OWNER TO enchere;

--
-- Name: settings; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.settings (
    id integer NOT NULL,
    key character varying(255) NOT NULL,
    creation_date timestamp with time zone DEFAULT now()
);


ALTER TABLE public.settings OWNER TO enchere;

--
-- Name: settings_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.settings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.settings_id_seq OWNER TO enchere;

--
-- Name: settings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.settings_id_seq OWNED BY public.settings.id;


--
-- Name: settings_value_history; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.settings_value_history (
    settings_id integer NOT NULL,
    id integer NOT NULL,
    value character varying(255) NOT NULL,
    date timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.settings_value_history OWNER TO enchere;

--
-- Name: settings_value_history_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.settings_value_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.settings_value_history_id_seq OWNER TO enchere;

--
-- Name: settings_value_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.settings_value_history_id_seq OWNED BY public.settings_value_history.id;


--
-- Name: user_photos; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.user_photos (
    id integer NOT NULL,
    user_id integer NOT NULL,
    picture bytea NOT NULL
);


ALTER TABLE public.user_photos OWNER TO enchere;

--
-- Name: user_photos_id_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.user_photos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_photos_id_seq OWNER TO enchere;

--
-- Name: user_photos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: enchere
--

ALTER SEQUENCE public.user_photos_id_seq OWNED BY public.user_photos.id;


--
-- Name: v_app_user; Type: TABLE; Schema: public; Owner: enchere
--

CREATE TABLE public.v_app_user (
    id bigint NOT NULL,
    money_can_use real,
    app_userid bigint
);


ALTER TABLE public.v_app_user OWNER TO enchere;

--
-- Name: v_app_user_seq; Type: SEQUENCE; Schema: public; Owner: enchere
--

CREATE SEQUENCE public.v_app_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.v_app_user_seq OWNER TO enchere;

--
-- Name: v_auction_with_state; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_auction_with_state AS
 SELECT auction.id,
    auction.description,
    auction.app_user_id,
    auction.category_id,
    auction.start_date,
    auction.end_date,
    auction.starting_price,
    auction.bid_step,
    auction.title,
        CASE
            WHEN (auction.start_date > now()) THEN 1
            WHEN (auction.end_date > now()) THEN 2
            WHEN (auction.end_date < now()) THEN 3
            ELSE NULL::integer
        END AS state_id
   FROM public.auction;


ALTER TABLE public.v_auction_with_state OWNER TO enchere;

--
-- Name: v_auction_with_top_bid; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_auction_with_top_bid AS
 SELECT a.id AS auction_id,
    a.description,
    a.app_user_id,
    a.start_date,
    a.end_date,
    a.starting_price,
    a.bid_step,
    a.title,
    t.id AS top_bid_id,
    COALESCE(t.amount, (0)::real) AS top_bid_amount,
    t.date AS top_bid_date,
    t.app_user_id AS top_bid_app_user_id,
    v.state_id,
    a.commission_rate_id,
    svh.value AS commission_rate,
        CASE
            WHEN (a.end_date < now()) THEN (COALESCE(t.amount, (0)::real) * (svh.value)::real)
            ELSE (0)::real
        END AS commission_amount
   FROM (((public.auction a
     LEFT JOIN LATERAL public.topbid(a.id) t(id, amount, date, app_user_id, auction_id) ON ((a.id = t.auction_id)))
     JOIN public.v_auction_with_state v ON ((a.id = v.id)))
     JOIN public.settings_value_history svh ON ((a.commission_rate_id = svh.id)));


ALTER TABLE public.v_auction_with_top_bid OWNER TO enchere;

--
-- Name: v_auctions_data; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_auctions_data AS
 SELECT COALESCE(s.date, e.date) AS date,
    COALESCE(s.started, (0)::bigint) AS started,
    COALESCE(e.ended, (0)::bigint) AS ended
   FROM (( SELECT (auction.start_date)::date AS date,
            count((auction.start_date)::date) AS started
           FROM public.auction
          GROUP BY ((auction.start_date)::date)) s
     FULL JOIN ( SELECT (auction.end_date)::date AS date,
            count((auction.end_date)::date) AS ended
           FROM public.auction
          GROUP BY ((auction.end_date)::date)) e ON ((s.date = e.date)));


ALTER TABLE public.v_auctions_data OWNER TO enchere;

--
-- Name: v_auctions_stats; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_auctions_stats AS
 SELECT row_number() OVER (ORDER BY COALESCE(date(g.g), auctions.date)) AS id,
    COALESCE(date(g.g), auctions.date) AS date,
    COALESCE(auctions.started, (0)::bigint) AS started,
    COALESCE(auctions.ended, (0)::bigint) AS ended
   FROM (public.v_auctions_data auctions
     RIGHT JOIN generate_series((( SELECT v_auctions_data.date
           FROM public.v_auctions_data
          ORDER BY v_auctions_data.date
         LIMIT 1))::timestamp with time zone, (( SELECT v_auctions_data.date
           FROM public.v_auctions_data
          ORDER BY v_auctions_data.date DESC
         LIMIT 1))::timestamp with time zone, '1 day'::interval) g(g) ON ((date(g.g) = auctions.date)))
  ORDER BY COALESCE(date(g.g), auctions.date);


ALTER TABLE public.v_auctions_stats OWNER TO enchere;

--
-- Name: v_daily_turnover; Type: VIEW; Schema: public; Owner: enchere
--

CREATE VIEW public.v_daily_turnover AS
 SELECT row_number() OVER (ORDER BY (date(g.g))) AS id,
    sum(COALESCE(v.top_bid_amount, (0)::real)) AS turnover,
    date(g.g) AS date
   FROM (public.v_auction_with_top_bid v
     RIGHT JOIN generate_series(( SELECT v_auction_with_top_bid.end_date
           FROM public.v_auction_with_top_bid
          ORDER BY v_auction_with_top_bid.end_date
         LIMIT 1), ( SELECT v_auction_with_top_bid.end_date
           FROM public.v_auction_with_top_bid
          WHERE (v_auction_with_top_bid.end_date < now())
          ORDER BY v_auction_with_top_bid.end_date DESC
         LIMIT 1), '1 day'::interval) g(g) ON (((v.end_date)::date = date(g.g))))
  WHERE (v.end_date < now())
  GROUP BY (date(g.g))
  ORDER BY (date(g.g));


ALTER TABLE public.v_daily_turnover OWNER TO enchere;

--
-- Name: admin id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin ALTER COLUMN id SET DEFAULT nextval('public.admin_id_seq'::regclass);


--
-- Name: admin_token id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token ALTER COLUMN id SET DEFAULT nextval('public.admin_token_id_seq'::regclass);


--
-- Name: app_user id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq'::regclass);


--
-- Name: app_user_token id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token ALTER COLUMN id SET DEFAULT nextval('public.app_user_token_id_seq'::regclass);


--
-- Name: auction id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction ALTER COLUMN id SET DEFAULT nextval('public.auction_id_seq'::regclass);


--
-- Name: auction_state id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_state ALTER COLUMN id SET DEFAULT nextval('public.auction_state_id_seq'::regclass);


--
-- Name: bid_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history ALTER COLUMN id SET DEFAULT nextval('public.bid_history_id_seq'::regclass);


--
-- Name: bid_photos id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_photos ALTER COLUMN id SET DEFAULT nextval('public.bid_photos_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: daily_auction id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_auction ALTER COLUMN id SET DEFAULT nextval('public.daily_auction_id_seq'::regclass);


--
-- Name: daily_sales id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_sales ALTER COLUMN id SET DEFAULT nextval('public.daily_sales_id_seq'::regclass);


--
-- Name: genrder id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.genrder ALTER COLUMN id SET DEFAULT nextval('public.genrder_id_seq'::regclass);


--
-- Name: reload_request id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request ALTER COLUMN id SET DEFAULT nextval('public.app_user_recharge_request_id_seq'::regclass);


--
-- Name: reload_request_state_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history ALTER COLUMN id SET DEFAULT nextval('public.app_user_recharge_state_history_id_seq'::regclass);


--
-- Name: reload_state id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_state ALTER COLUMN id SET DEFAULT nextval('public.recharge_state_id_seq'::regclass);


--
-- Name: settings id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings ALTER COLUMN id SET DEFAULT nextval('public.settings_id_seq'::regclass);


--
-- Name: settings_value_history id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history ALTER COLUMN id SET DEFAULT nextval('public.settings_value_history_id_seq'::regclass);


--
-- Name: user_photos id; Type: DEFAULT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.user_photos ALTER COLUMN id SET DEFAULT nextval('public.user_photos_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.admin (id, username, email, password) FROM stdin;
1	Jean	Jean@gmail.com	123
\.


--
-- Data for Name: admin_token; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.admin_token (id, value, expiration_date, creation_date, adminid) FROM stdin;
562	e627e4af971aaaca5403b2f5572e3e7361ffbc18	2023-01-22 14:37:40.534323	2023-01-22 13:37:40.536325	1
\.


--
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user (id, username, email, password, account_balance, birth_date, gender_id, register_date) FROM stdin;
1	rkt_01	Rakoto.Mario@gmail.com	123	0	2002-01-12	1	2022-01-01
2	rasoaJ	Rasoa.Jeanne@yahoo.fr	123	0	2001-02-24	2	2023-01-12
3	egooble0	dreichartz0@github.com	OoBXRB	0	1999-08-12	2	2021-05-17
4	rmilley1	cbradberry1@yale.edu	TVEdASuBgabW	0	1995-11-05	1	2021-03-17
5	msolomon2	ccritoph2@cyberchimps.com	wcS0yr	0	1999-03-01	1	2021-04-08
6	dbenardet3	hebsworth3@yahoo.com	LhS0C8j	0	1991-05-25	1	2021-08-06
7	dgunson4	vdarkott4@yahoo.co.jp	rhVm7L69Y95M	0	1996-05-17	2	2021-12-16
8	kattreed5	kszachniewicz5@epa.gov	GbeOvIjGiR	0	1991-06-30	1	2021-11-15
9	lwingeatt6	bast6@tinyurl.com	Y0XxSBnem8o7	0	1997-01-28	1	2021-05-24
10	abaughan7	rmacshirie7@china.com.cn	0QbpP6	0	2001-03-26	2	2021-06-28
11	nbecket8	cbroyd8@seattletimes.com	zIIRSCJYP	0	1998-03-31	1	2021-12-26
12	aboggish9	tlavery9@hud.gov	ojQtJFo	0	1993-12-31	2	2021-12-07
13	dpallasa	kluckesa@altervista.org	v3dMwWZ6nj5	0	1997-12-21	1	2021-05-24
14	ifillimoreb	lkelcherb@cam.ac.uk	MOKc1LLKPu	0	2001-03-01	2	2021-01-14
15	abroadwellc	eheballc@behance.net	z36ADj	0	1995-02-10	2	2021-04-20
16	ktremayned	nassenderd@vkontakte.ru	lsmBxh1Fb	0	1994-06-09	1	2021-10-11
17	mlacroutse	hthrustlee@dot.gov	gwNaxYlW7MK	0	1993-08-06	1	2021-08-05
18	mwylderf	btwigginsf@noaa.gov	YeccQqdN	0	2001-02-15	2	2021-10-16
19	csandsg	cfetherstoneg@technorati.com	oTLKMzRkxkw	0	1990-05-08	1	2021-07-13
20	sbraileyh	aroddenh@shop-pro.jp	8j0cfkQ4	0	1994-06-01	1	2021-10-07
21	lbreami	wsmizi@ted.com	dzo5j3HHIy	0	1999-10-04	2	2021-02-19
22	aarmytagej	pkannj@typepad.com	OkU4Mp	0	1991-04-02	2	2021-11-26
\.


--
-- Data for Name: app_user_token; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.app_user_token (id, value, expiration_date, creation_date, app_userid, app_userdescription) FROM stdin;
\.


--
-- Data for Name: auction; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.auction (id, description, app_user_id, category_id, start_date, end_date, starting_price, bid_step, title, commission_rate_id, app_userid, auction_stateid) FROM stdin;
10	Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.	1	2	2022-09-24 02:50:04+03	2022-12-26 14:04:40+03	98000	16000	Coffee - Egg Nog Capuccino	7	\N	\N
1	Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.	2	3	2022-11-26 07:51:33+03	2022-12-25 22:40:01+03	81000	19000	Puff Pastry - Slab	7	\N	\N
5	Ut at dolor quis odio consequat varius. Integer ac leo.	1	1	2022-08-17 03:39:40+03	2022-12-28 02:35:04+03	39000	5000	Marzipan 50/50	7	\N	\N
4	Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.	1	1	2022-09-03 18:02:13+03	2022-12-26 16:29:25+03	89000	19000	Filter - Coffee	7	\N	\N
3	In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum.	1	2	2022-11-06 22:48:29+03	2022-12-28 05:09:04+03	41000	16000	Napkin - Dinner, White	7	\N	\N
2	Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique.	1	1	2022-10-21 18:27:52+03	2022-12-18 09:55:12+03	90000	14000	Plastic Arrow Stir Stick	7	\N	\N
9	Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio.	1	3	2022-11-06 18:45:31+03	2022-12-22 03:32:52+03	60000	11000	Sole - Iqf	7	\N	\N
8	Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.	2	1	2022-09-10 23:24:57+03	2022-12-27 12:12:39+03	83000	14000	Squash - Sunburst	7	\N	\N
7	Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.	2	1	2022-10-21 18:24:26+03	2022-12-20 22:46:05+03	76000	20000	Bar Bran Honey Nut	7	\N	\N
6	Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum.	1	1	2022-09-23 20:34:03+03	2022-12-19 21:46:11+03	94000	16000	Foil Cont Round	7	\N	\N
\.


--
-- Data for Name: auction_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.auction_state (id, value) FROM stdin;
1	Créée
2	En cours
3	Terminée
\.


--
-- Data for Name: bid_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.bid_history (id, amount, date, app_user_id, auction_id) FROM stdin;
2	81000	2022-11-27 03:00:00+03	3	1
3	100000	2022-11-28 03:00:00+03	4	1
4	138000	2022-11-29 03:00:00+03	5	1
5	90000	2022-10-22 03:00:00+03	6	2
6	104000	2022-10-23 03:00:00+03	7	2
7	142000	2022-10-24 03:00:00+03	8	2
\.


--
-- Data for Name: bid_photos; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.bid_photos (id, bid_id, picture) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.category (id, name) FROM stdin;
2	Décoration
3	Artistique
1	Technologie
4	Produits ménagers
5	Autres
\.


--
-- Data for Name: daily_auction; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.daily_auction (id, amount, date) FROM stdin;
\.


--
-- Data for Name: daily_sales; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.daily_sales (id, amount, date) FROM stdin;
\.


--
-- Data for Name: genrder; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.genrder (id, name) FROM stdin;
1	Homme
2	Femme
\.


--
-- Data for Name: reload_request; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_request (id, date, amount, app_user_id) FROM stdin;
102	2023-01-21 13:57:51.6412+03	150000	1
152	2023-01-21 13:59:27.018081+03	200000	2
\.


--
-- Data for Name: reload_request_state_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_request_state_history (id, date, reload_request_id, reload_state_id) FROM stdin;
1	2023-01-21 13:57:51.6412+03	102	1
2	2023-01-21 13:59:27.018081+03	152	1
3	2023-01-21 17:13:37.091718+03	102	1
4	2023-01-21 17:13:50.450371+03	152	1
7	2023-01-21 17:26:13.096941+03	102	3
8	2023-01-21 17:26:20.534177+03	152	2
\.


--
-- Data for Name: reload_state; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.reload_state (id, description) FROM stdin;
1	En cours de traitement
2	Acceptée
3	Refusée
\.


--
-- Data for Name: settings; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.settings (id, key, creation_date) FROM stdin;
2	Durée d'une enchère (en jours)	2023-01-21 21:00:36.136105+03
1	Durée de validité d'un jeton (en secondes)	2023-01-21 22:16:15.755798+03
3	test	2023-01-21 22:24:16.081596+03
4	Age minimum	2023-01-21 22:36:42.772894+03
5	Prix de vente minimum	2023-01-21 22:36:42.832892+03
6	Commision	2023-01-22 10:49:11.198155+03
\.


--
-- Data for Name: settings_value_history; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.settings_value_history (settings_id, id, value, date) FROM stdin;
1	1	3600	2023-01-21 03:00:00+03
2	2	30	2023-01-21 03:00:00+03
1	3	360	2023-01-21 22:16:34.067675+03
3	4	super	2023-01-21 22:24:17.348366+03
4	5	18	2023-01-21 22:36:42.782927+03
5	6	10000	2023-01-21 22:36:42.837893+03
6	7	0.2	2023-01-22 10:49:23.247412+03
\.


--
-- Data for Name: user_photos; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.user_photos (id, user_id, picture) FROM stdin;
\.


--
-- Data for Name: v_app_user; Type: TABLE DATA; Schema: public; Owner: enchere
--

COPY public.v_app_user (id, money_can_use, app_userid) FROM stdin;
\.


--
-- Name: admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_id_seq', 1, false);


--
-- Name: admin_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_seq', 1, false);


--
-- Name: admin_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_token_id_seq', 1, false);


--
-- Name: admin_token_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.admin_token_seq', 601, true);


--
-- Name: app_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_id_seq', 22, true);


--
-- Name: app_user_recharge_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_request_id_seq', 1, false);


--
-- Name: app_user_recharge_request_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_request_seq', 1, false);


--
-- Name: app_user_recharge_state_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_state_history_id_seq', 8, true);


--
-- Name: app_user_recharge_state_history_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_recharge_state_history_seq', 1, false);


--
-- Name: app_user_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_seq', 1, false);


--
-- Name: app_user_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_token_id_seq', 1, false);


--
-- Name: app_user_token_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.app_user_token_seq', 1, false);


--
-- Name: auction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_id_seq', 1, false);


--
-- Name: auction_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_seq', 1, false);


--
-- Name: auction_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_state_id_seq', 3, true);


--
-- Name: auction_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.auction_state_seq', 1, false);


--
-- Name: bid_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_history_id_seq', 7, true);


--
-- Name: bid_history_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_history_seq', 1, false);


--
-- Name: bid_photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.bid_photos_id_seq', 1, false);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.category_id_seq', 5, true);


--
-- Name: category_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.category_seq', 1, false);


--
-- Name: daily_auction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_auction_id_seq', 1, false);


--
-- Name: daily_auction_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_auction_seq', 1, false);


--
-- Name: daily_sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.daily_sales_id_seq', 1, false);


--
-- Name: genrder_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.genrder_id_seq', 1, false);


--
-- Name: recharge_state_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.recharge_state_id_seq', 3, true);


--
-- Name: recharge_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.recharge_state_seq', 1, false);


--
-- Name: reload_request_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_request_seq', 201, true);


--
-- Name: reload_request_state_history_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_request_state_history_seq', 51, true);


--
-- Name: reload_state_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.reload_state_seq', 1, false);


--
-- Name: settings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.settings_id_seq', 6, true);


--
-- Name: settings_value_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.settings_value_history_id_seq', 7, true);


--
-- Name: user_photos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.user_photos_id_seq', 1, false);


--
-- Name: v_app_user_seq; Type: SEQUENCE SET; Schema: public; Owner: enchere
--

SELECT pg_catalog.setval('public.v_app_user_seq', 1, false);


--
-- Name: admin admin_email_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_email_key UNIQUE (email);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: admin_token admin_token_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token
    ADD CONSTRAINT admin_token_pkey PRIMARY KEY (id);


--
-- Name: admin admin_username_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_username_key UNIQUE (username);


--
-- Name: app_user app_user_email_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_email_key UNIQUE (email);


--
-- Name: app_user app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (id);


--
-- Name: reload_request app_user_recharge_request_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request
    ADD CONSTRAINT app_user_recharge_request_pkey PRIMARY KEY (id);


--
-- Name: reload_request_state_history app_user_recharge_state_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT app_user_recharge_state_history_pkey PRIMARY KEY (id);


--
-- Name: app_user_token app_user_token_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token
    ADD CONSTRAINT app_user_token_pkey PRIMARY KEY (id);


--
-- Name: app_user app_user_username_key; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT app_user_username_key UNIQUE (username);


--
-- Name: auction auction_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT auction_pkey PRIMARY KEY (id);


--
-- Name: auction_state auction_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction_state
    ADD CONSTRAINT auction_state_pkey PRIMARY KEY (id);


--
-- Name: bid_history bid_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT bid_history_pkey PRIMARY KEY (id);


--
-- Name: bid_photos bid_photos_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_photos
    ADD CONSTRAINT bid_photos_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: daily_auction daily_auction_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_auction
    ADD CONSTRAINT daily_auction_pkey PRIMARY KEY (id);


--
-- Name: daily_sales daily_sales_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.daily_sales
    ADD CONSTRAINT daily_sales_pkey PRIMARY KEY (id);


--
-- Name: genrder genrder_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.genrder
    ADD CONSTRAINT genrder_pkey PRIMARY KEY (id);


--
-- Name: reload_state recharge_state_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_state
    ADD CONSTRAINT recharge_state_pkey PRIMARY KEY (id);


--
-- Name: settings settings_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings
    ADD CONSTRAINT settings_pkey PRIMARY KEY (id);


--
-- Name: settings_value_history settings_value_history_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history
    ADD CONSTRAINT settings_value_history_pkey PRIMARY KEY (id);


--
-- Name: user_photos user_photos_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.user_photos
    ADD CONSTRAINT user_photos_pkey PRIMARY KEY (id);


--
-- Name: v_app_user v_app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_app_user
    ADD CONSTRAINT v_app_user_pkey PRIMARY KEY (id);


--
-- Name: auction_state_id; Type: INDEX; Schema: public; Owner: enchere
--

CREATE INDEX auction_state_id ON public.auction_state USING btree (id);


--
-- Name: auction auction_settings_value_history_null_fk; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT auction_settings_value_history_null_fk FOREIGN KEY (commission_rate_id) REFERENCES public.settings_value_history(id);


--
-- Name: admin_token fkadmin_toke154889; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.admin_token
    ADD CONSTRAINT fkadmin_toke154889 FOREIGN KEY (adminid) REFERENCES public.admin(id);


--
-- Name: app_user fkapp_user427587; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user
    ADD CONSTRAINT fkapp_user427587 FOREIGN KEY (gender_id) REFERENCES public.genrder(id);


--
-- Name: reload_request_state_history fkapp_user_r279062; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT fkapp_user_r279062 FOREIGN KEY (reload_state_id) REFERENCES public.reload_state(id);


--
-- Name: reload_request_state_history fkapp_user_r630701; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request_state_history
    ADD CONSTRAINT fkapp_user_r630701 FOREIGN KEY (reload_request_id) REFERENCES public.reload_request(id);


--
-- Name: reload_request fkapp_user_r80032; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.reload_request
    ADD CONSTRAINT fkapp_user_r80032 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: app_user_token fkapp_user_t445450; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.app_user_token
    ADD CONSTRAINT fkapp_user_t445450 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction fkauction314945; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkauction314945 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: auction fkauction439414; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkauction439414 FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: v_app_user fkbi8i5utyyhi8bkpg49wmca7lu; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.v_app_user
    ADD CONSTRAINT fkbi8i5utyyhi8bkpg49wmca7lu FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: bid_history fkbid_histor407877; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkbid_histor407877 FOREIGN KEY (auction_id) REFERENCES public.auction(id);


--
-- Name: bid_history fkbid_histor887760; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.bid_history
    ADD CONSTRAINT fkbid_histor887760 FOREIGN KEY (app_user_id) REFERENCES public.app_user(id);


--
-- Name: auction fkhq6yj95ecwfle2h477um77te7; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkhq6yj95ecwfle2h477um77te7 FOREIGN KEY (app_userid) REFERENCES public.app_user(id);


--
-- Name: auction fkrkfxjkivfq795yy1cqybxkqv5; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT fkrkfxjkivfq795yy1cqybxkqv5 FOREIGN KEY (auction_stateid) REFERENCES public.auction_state(id);


--
-- Name: settings_value_history fksettings_v92068; Type: FK CONSTRAINT; Schema: public; Owner: enchere
--

ALTER TABLE ONLY public.settings_value_history
    ADD CONSTRAINT fksettings_v92068 FOREIGN KEY (settings_id) REFERENCES public.settings(id);


--
-- PostgreSQL database dump complete
--

