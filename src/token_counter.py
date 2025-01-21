import tiktoken

encoder = tiktoken.encoding_for_model("text-embedding-ada-002")
text = """{
"remise": "TG",
"moyensPedagogiques": "<p>Les formations PLB sont conues et animes par des experts en activit.<br>Les <strong>cours pratiques</strong> alternent <strong>travaux pratiques</strong>, concrets et progressifs construits sous forme de projet fil rouge, <strong>et apports thoriques</strong> (en moyenne 60% de travaux pratiques, 40% de thorie).<br>Les <strong>sminaires</strong> font alterner <strong>tudes de cas et dmonstrations</strong> concrtes, issues de lexprience terrain de nos formateurs, <strong>et apports thoriques.</strong><br>Le support de cours et le cahier dexercices incluant les corrigs sont fournis en franais, au format PDF, et sont tlchargeables.<br>Chaque participant, en prsentiel ou en classe virtuelle, accde aux travaux pratiques ou aux dmonstrations via un poste de travail qui lui est ddi, configur et quip des logiciels et outils spcifiques prsents dans cette formation, dans leur dernire version.<br>Les cours et examens de certification officiels suivent les conditions de lditeur, du constructeur ou du certificateur. Ils sont susceptibles d'voluer  tout moment.<br>La plupart des formations peuvent tre suivies indiffremment en prsentiel ou en classe  distance synchrone depuis votre entreprise ou votre domicile (voir les <a href=\"https://www.plb.fr/formation-a-distance.php\">Modalits pdagogiques, techniques et de suivi</a> spcifiques aux formations  distance).<br>Les salles sont quipes pour accueillir ces deux modalits: vidoprojecteur, cran/tableau interactif, webcam et sonorisation, accs Internet trs haut dbit et espace documentaire partag.<br>Laccs aux ressources pdagogiques, techniques et de suivi seffectue via un espace personnel 100% digital. Le service dassistance technique est joignable par chat et tlphone avant et pendant la formation.<br>Nos btiments sont classs ERP 5. En cas de handicap, contactez-nous par tlphone ou via ladresse accesssibilite@plb.fr afin de mettre en place l'quipement et l'accompagnement adapts.</p>",
"modalitesSuivi": "<p>Les pr-requis sont valus <strong>par QCM</strong> avant lentre en formation.<br>Les participants ralisent, <strong>en dbut et en fin de formation, une auto-valuation</strong> de leurs connaissances et comptences au regard des objectifs pdagogiques de la formation.<br>Lvaluation des acquis en cours de formation est ralise au fur et  mesure <strong>des tudes de cas et/ou Travaux Pratiques</strong> (50% du temps minimum pour les cours pratiques) <strong>et/ou sous forme de QCM.</strong><br>L'valuation en fin de formation est ralise de la mme faon&nbsp; et/ou via <strong>le questionnaire dauto-valuation</strong> qui permet de mesurer l'volution par rapport au dbut de la formation et/ou par le passage de l'examen de certification, le cas chant.<br>Les convocations, avec horaires dfinitifs, lieu et plan d'accs, sont envoyes deux semaines avant le dbut de la formation.<br>Les participants en classe virtuelle reoivent leurs lments de connexion par e-mail.<br>La feuille de prsence numrique est  signer par demi-journe.<br>En fin de formation, les participants remplissent un questionnaire de satisfaction global.<br>Une attestation de ralisation est remise  la fin de la formation.<br>Si les conditions le permettent, nous pouvons inscrire jusqu' 24h avant le dbut de la session. En cas dinscription via le site Moncompteformation, un dlai de 11 jours ouvrs minimum est  respecter entre la proposition de commande et l'entre en formation.</p>",
"nouveaute": "non",
"tarifIntra": "",
"reference": "INAR",
"libelle": "Intelligence Artificielle (IA) : concepts, enjeux, technologies et stratgies pour les entreprises ",
"duree": 2,
"prix": 1850.0,
"tarifFormateur": null,
"origine": "PLB / F. BARDOL",
"support": false,
"distance": "Oui",
"elearning": false,
"niveau": "Fondamental",
"autreObjectifSimple": null,
"eligibleCpf": false,
"codeCpf": "",
"statut": "",
"archivedDate": null,
"dateCreation": "2018-02-07T16:56:02.000+00:00",
"dateModification": "2024-06-04T13:41:54.000+00:00",
"url": "formation-intelligence-artificielle",
"libelleTop10": "",
"blocCommercialisation": "<p><strong>Ce cours fait partie de l'action collective </strong><span style=\"background-color:yellow;\"><strong>Atlas&nbsp;Big Data, IA et IoT</strong></span><strong>  compter du 02/01/2023, module 6.S &nbsp;19.&nbsp;tat de l'art sur l'Intelligence Artificielle .&nbsp;</strong></p>",
"blocPublic": "",
"blocTarif": null,
"blocCertification": null,
"blocCpf": null,
"blocMutualisation": null,
"blocPartenaires": "<p><span style=\"background-color:#ff0000;color:#ffffff;\">Rouge pour IB</span>, leur programme est plus complet.</p>",
"sousTitre": "Big Data, Machine Learning, Deep Learning, Algorithmes",
"visible": "oui",
"typePlanif": "Plb Inter",
"coursPlbCataloguePartenaire": "",
"motClePrimaire": "Intelligence Artificielle",
"baliseKeywords": "Formation IA, Formation Initiation Intelligence Artificielle, ActionCoAtlas, ActionCoAtlasBigData, Action collective atlas, action collective atlas Big Data IA et IoT, Action collective fafiec, formation atlas Intelligence Artificielle, LAPOSTELOT5vivierformation",
"baliseDescription": "Cette formation Intelligence Artificielle dmystifie lIntelligence Artificielle (IA) et donne aux cadres et dirigeants les cls et les outils ncessaires  sa matrise.",
"baliseTitle": "Formation Intelligence Artificielle - Matriser les Concepts | PLB",
"campagneAdwords": "SEA Big Data - BI /IA",
"descriptif": "<p>LIntelligence Artificielle (IA) est ne au milieu des annes 50 dans les universits amricaines. Elle connat aujourdhui un cho considrable et suscite des craintes et des interrogations jusque dans les milieux politiques. En effet, ces dernires annes ont vu apparatre des ralisations tangibles qui semblaient totalement irralisables il y a encore peu. Des programmes intelligents parviennent  supplanter lhomme dans des activits qui lui taient jusqualors rserves (reconnaissance visuelle, criture automatique darticles, vhicules sans chauffeur, ventes prdictives, robot de conversation, traduction automatique). Des pans entiers du business sont bouleverss par larrive dacteurs matrisant parfaitement ces nouveaux outils. Toutes les industries sont trs fortement impactes par ces technologies qui dstabilisent des forces existantes. La comprhension de ces nouveaux outils technologiques savre dsormais un enjeu crucial pour les dcideurs et les architectes SI.</p><p>Ce sminaire vise avant tout  sensibiliser les participants  lvolution du march informatique, des mtiers et des technologies induites par lIA dans sa maturit actuelle et  venir. Il sagira pour eux de disposer du vocabulaire, de comprendre les technologies et leurs enjeux afin dtre capables de mesurer les apports de lIA que ce soit au niveau conceptuel ou technique et son impact sur les mtiers. Cette formation aura galement pour objectif de prparer au mieux les stagiaires  analyser les apports potentiels de lIA dans leurs projets tout en disposant dlments concrets pour sa mise en uvre et dun vocabulaire commun.</p>",
"contenu": "<span class=\"sous_titre\">Jour 1</span><h3 class=\"titre\">Introduction  lintelligence Artificielle</h3><span class=\"corps_contenu\">Dfinir lIA par des exemples concrets<br>Historique et concepts actuels<br>Situer les notions clefs autour de lIA</span><h3 class=\"titre\">Les briques technologiques de l'Intelligence Artificielle (IA)</h3><span class=\"corps_contenu\">Dmonstrations d'utilisations de l'IA, classification des technologies<br>Les frameworks de dveloppement, panoramas et comparaison<br>Le rle stratgique des donnes, phases de traitement des donnes</span><h3 class=\"titre\">Introduction au Deep Learning et Machine Learning</h3><span class=\"corps_contenu\">Comprendre ce quest le Machine Learning ainsi que le Deep Learning<br>Quelles sont les diffrences ?&nbsp;<br>Le machine-learning et les problmatiques auxquels il rpond<br>Le deep-learning et l'apprentissage profond, ses applications</span><span class=\"corps_travaux_pratiques\">Quiz sur le vocabulaire pour que tout le monde dispose dun langage commun durant la formation (IA, ML, Deep Learning, Frameworks, etc.)</span><h3 class=\"titre\">Applications gnriques de l'IA</h3><span class=\"corps_contenu\">Le domaine de l'audio : reconnaissance, gnration<br>Traitement du langage naturel : classification, traduction, conversation<br>L'image et la vido : segmentation, suivi, voiture autonome, robotique</span><span class=\"titre_travaux_pratiques\">Dmonstrations</span><span class=\"corps_travaux_pratiques\">Analyse de sentiment en utilisant le texte ou laudio (personne agressive ou reposante) afin doffrir un service client de meilleur qualit.&nbsp;<br>Fonctionnement voiture autonome (analyse en temps rel des objets du voisinage sous forme cartographi avec pourcentage derreur dans la reconnaissance des vhicules).</span><h3 class=\"titre\">Les applications mtier de l'intelligence artificielle</h3><span class=\"corps_contenu\">Management : aide  la dcision, dtection de KPI<br>Marketing et commercial : segmentation client, dtection de churn<br>Organisation de l'entreprise, productivit<br>Services techniques : maintenance prdictive, surveillance d'infrastructure, chane de fabrication, optimisation, consommation</span><span class=\"titre_travaux_pratiques\">Dmonstration</span><span class=\"corps_travaux_pratiques\">Exemple de rendu dun programme crit en Python pour valuer les risques de noyades (identification des personnes  risque en fonction de leurs positions par rapport au point deau : plage, piscine, etc.). Les personnes apparaissent encadres en temps rel sur la vido de surveillance.</span><span class=\"sous_titre\">Jour 2</span><h3 class=\"titre\">IA et infrastructure informatique</h3><span class=\"corps_contenu\">IA et Big Data : l'importance de la donne<br>Impacts sur l'infrastructure requise&nbsp;<br>Principes gnraux des clusters&nbsp;<br>Exemple de lcosysme Hadoop<br>Loffre des grands acteurs du Cloud (Amazon, Google, Microsoft)<br>Le principe de linfrastructure as a service (IaaS)<br>Les outils pour nous aider  capter la donne dans un contexte rparti</span><span class=\"titre_travaux_pratiques\">Dmonstration</span><span class=\"corps_travaux_pratiques\">Visite de loffre en infrastructure Informatique pour lIA propose par Amazon. Lide est surtout de disposer dune synthse claire de ce que doit tre une infrastructure type qui supporte des applications dintelligence artificielle mais aussi des services annexes proposs afin de disposer dune vue globale sur linfrastructure (stockage des donnes, traitements, analyses, etc.)</span><h3 class=\"titre\">Les projets IA en entreprise</h3><span class=\"corps_contenu\">Comment s'organise un projet li  l'IA<br>L'importance de la gouvernance des donnes<br>Les nouveaux rles de la DSI, les nouvelles comptences<br>Deep Learning VS Machine Learning<br>Situer lIA dans des projets complexes</span><span class=\"titre_travaux_pratiques\">Dmonstrations</span><span class=\"corps_travaux_pratiques\">Exemples de projets russis ou lutilisation de lIA a t voque pour finalement ne pas tre retenue.<br>Exemple de rorganisation (quipes, phases du projet, etc.) lors de lintroduction de lIA dans un projet et la place fondamentale prise par la gouvernance des donnes afin dalimenter de faon fiable et performante lenvironnement IA sous-jacent souvent complexe (infrastructure rpartie, spcificits du traitement des gros volumes de donnes, technologies Cloud, etc.).</span><h3 class=\"titre\">Les acteurs de l'IA</h3><span class=\"corps_contenu\">L'IA d'aujourd'hui, entre ralit et effet marketing<br>Le positionnement des GAFAM, l'effet de l'IA sur leur march<br>Les solutions innovantes les plus marquantes<br>Le positionnement de la France</span><span class=\"titre_travaux_pratiques\">Dmonstrations</span><span class=\"corps_travaux_pratiques\">Exemples concrets des grands projets en France et  ltranger actuellement en production  et en phase dtude.</span><h3 class=\"titre\">Impact socital</h3><span class=\"corps_contenu\">La scurit et les impacts sur la vie prive<br>La vision europenne de la scurit (RGPD)<br>Responsabilit et thique<br>Impact socital et cohabitation avec l'IA</span><span class=\"titre_travaux_pratiques\">Dmonstrations</span><span class=\"corps_travaux_pratiques\">Biais de lIA , failles du systme, comment les contrer ?<br>Exemples derreur li  la base dapprentissage quand le systme arrive  de mauvaises conclusions sur la reconnaissance faciale homme ? femme ? car lchantillon de personnes contient des informations biaises (trop de personnes du mme type).<br>Tour dhorizon des impacts socitaux concrets dans diffrents domaines comme la sant, lducation, les villes intelligentes, etc.</span>",
"objectifs_operationnels": "<p><strong>Objectif oprationnel</strong> :</p><p>Possder une vision globale, des lments concrets et un vocabulaire commun pour se prparer  un projet intgrant de l'Intelligence Artificielle.</p>",
"objectifs_pedagogiques": "<p><strong>Objectifs pdagogiques</strong> :</p><p> l'issue de cette <strong>formation Intelligence Artificielle</strong>, vous aurez acquis les connaissances et comptences ncessaires pour :</p><ul><li>Dfinir lintelligence Artificielle, expliquer les concept clefs</li><li>Dterminer les apports de lIA selon les mtiers et les secteurs par des exemples dapplication</li><li>Identifier les briques technologiques, les outils et les infrastructures autour de lIA</li><li>Lister les acteurs indispensables  un projet intgrant de lIntelligence artificielle</li><li>Envisager limpact socital de lIA</li></ul>",
"prerequis": "<p>Il est ncessaire d'avoir une culture gnrale de base en informatique (OS, dveloppement, SGBD).</p>",
"participants": "<p>Ce cours Intelligence Artificielle s'adresse  tout acteur des SI d'entreprises : dcideur, DSI, utilisateur mtier, chef de projet, architecte, dveloppeur...<br>Elle pourra aussi intresser tout utilisateur mtier, consultant ou toute personne qui souhaite disposer dune vision densemble sur lIA et ses applications actuelles.</p>",
"travauxPratiques": null,
"libelleCertification": null,
"descriptifCertification": null,
"autre": "<p><img src=\"/download/pictures/logos-partenaires/logo-acn-atlas-couleur.png\" alt=\"Logo partenaire action collective de l'OPCO Atlas\"> <strong>Votre formation Intelligence Artificielle prise en charge jusqu' 100% des cots pdagogiques !</strong> *</p><p>Depuis 2011, PLB Consultant est partenaire des Actions Collectives Atlas pour vous proposer les meilleures formations informatique et dvelopper vos comptences dans le Numrique&nbsp;:</p><p><strong>-&nbsp;En prsentiel dans lune de nos salles en Rgions</strong>&nbsp;&nbsp;&nbsp;&nbsp;<br><strong>-&nbsp; distance depuis votre bureau</strong>&nbsp;&nbsp;&nbsp;&nbsp;<br><strong>- Sessions intra-entreprise dans toute la France.</strong></p><p>* jusqu' 100% des cots pdagogiques selon votre branche d'activit dans la limite des fonds mutualiss ddis aux actions collectives et en application des critres de prise en charge en vigueur (cf conditions dtailles sur <a href=\"https://www.opco-atlas.fr/criteres-financement.html\">le site d'Atlas</a>)</p>",
"top10": 0,
"certification": "",
"coursOfficiel": "",
"actionCo": null,
"categoriesSecondaires": [],
"categorie": {
    "libelle": "IA (Intelligence Artificielle)",
    "filiere": {
        "libelle": "Big Data - BI - IA",
        "url": "aide--la-dcision",
        "description": "<p>Dans le monde en constante volution des technologies de l'information, la <strong>filire Big Data - BI - IA</strong> se dmarque comme un ple essentiel pour les professionnels cherchant  matriser les donnes massives et  prendre des dcisions claires. Cette filire rassemble une gamme varie de comptences cruciales pour naviguer dans le paysage complexe de la gestion des donnes et de la Business Intelligence (BI).</p><p>Au cur de cette filire, vous explorerez des sujets tels que l'Aide  la dcision, le logiciel R, l'architecture et les concepts du Big Data, ainsi que l'analyse et la visualisation des donnes massives. Vous dvelopperez des comptences en matire de Big Data, en utilisant des outils, des solutions et des plateformes de pointe, tout en explorant l'Intelligence Artificielle (IA) pour exploiter au maximum le potentiel des donnes.</p><p>Nous proposons galement des formations spcialises sur des technologies cls telles que Hadoop, Kafka, Talend, Business Objects, Qlik, Crystal Reports, EssBase, et bien d'autres. Vous serez en mesure de matriser ces outils essentiels pour la collecte, le traitement, l'analyse, et la visualisation des donnes.</p><p>Explorez le potentiel des donnes massives avec nous et ouvrez la porte  de nouvelles opportunits passionnantes.</p>",
        "titre": "Big Data  BI  IA (Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik)",
        "metaTitre": "Formation Big Data- BI - IA : Les cours pour apprendre le Big Data et la BI | PLB",
        "metaDescription": "Dcouvrez nos formations Big Data, BI et IA. Des cours de tous niveaux pour apprendre ou se perfectionner en formation continue avec PLB. ",
        "metaKeywords": "formation big data, formation intelligence artificielle, formation ai, formation data mining, formation webintelligence, formation hadoop, formation Data warehouse, formation Data science, formation data analyse, formation dveloppement big data, formation data analyst, formation data scientist, formation data visualisation, formation dataviz, formation reporting, formation aide  la decision, formation Talend, formation etl, formation Qlik, formation langage R, formation business objects, formation crystal reports, formation essbase, formation Kafka",
        "miniDescription": "Pour apprendre l'IA, Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik...",
        "specialiteFormation": null
    },
    "description": "<p>L<strong>Intelligence Artificielle (IA)</strong> est ne au milieu des annes 50 dans les universits amricaines. Elle connat aujourdhui un cho considrable, en particulier depuis la popularisation des IA dites  gnratives  (type <strong>ChatGPT</strong>).</p><p>Ces dernires annes ont vu apparatre des ralisations tangibles qui semblaient totalement irralisables il y a encore peu. Des programmes intelligents parviennent  supplanter lhomme dans des activits qui lui taient jusqualors rserves (reconnaissance visuelle, criture automatique darticles, vhicules sans chauffeur, ventes prdictives, robot de conversation, traduction automatique). Des pans entiers du business sont bouleverss par larrive dacteurs matrisant parfaitement ces nouveaux outils tandis que de nouvelles comptences comme le <strong>prompt engineering</strong> font surface. Toutes les industries sont trs fortement impactes par ces technologies qui dstabilisent des forces existantes.&nbsp;</p><p>Cette catgorie IA (Intelligence Artificielle) regroupe toutes nos formations IA pour vous aider  tirer pleinement profit de ces nouvelles technologies. Tournes vers la pratique, ces <strong>formations IA</strong> visent  vous rendre oprationnel et autonome quant  la matrise de ces outils en constante volution.</p><p>Nhsitez pas  contacter notre service pdagogique et technique au <strong>01 43 34 90 94</strong> si vous ne trouvez pas la formation IA que vous souhaitez.<br>Nous pouvons galement vous accompagner pour monter une session de formation IA sur mesure en vos ou en nos locaux (formation intra-entreprise)</p>",
    "metaTitre": "Formation IA (Intelligence Artificielle): la liste de nos formations | PLB Consultant",
    "metaDescription": "Cette catgorie regroupe toutes nos formations IA : IA gnrative, ChatGPT, Green IA...",
    "metaKeywords": "formation IA, formation AI, formation intelligence artificielle, formation ChatGPT, formation Green IA, formation chatbot, formation IA gnrative, formation agent conversationnel, formation reconnaissance d'image, formation prompt engineer, formation engineering",
    "categoriesConnexes": [],
    "afficherHaut": 1,
    "specialiteFormation": null
},
"archived": false,
"categorieLibelle": "IA (Intelligence Artificielle)",
"autresCategories": [],
"partenaires": [
    {
        "id": 31,
        "nom": "IB",
        "web": "https://www.ib-formation.fr/",
        "remise": "<p><strong>Option repas  23/j.</strong></p><p><span style=\"background-color:hsl(60,75%,60%);\"><strong>Fichier de remise</strong></span> : \\\\plb-data\\plb\\Partenaires\\IB\\Cours remiss\\2023\\PLB - LISTE TARIFAIRE 2023.xlsx</p><p>OU</p><p>Se connecter  notre <span style=\"background-color:hsl(60,75%,60%);\"><strong>espace client/partenaire</strong></span> (en haut  droite sur le header de leur site)<br><strong>ID</strong> : Offre@plb.fr&nbsp;<br><strong>MDP</strong> : PLBconsultant1999!</p><p><u>ATTENTION, DEPUIS 2022 NOUS NE POUVONS PLUS PROPOSER LES COURS DE LA CEGOS</u></p>",
        "contactFormation": "Elisabeth DA CUNHA : elisabeth.da.cunha@ib.cegos.fr - 01 41 99 20 75 - 06 99 14 89 11",
        "contactCommercial": "Julia AMKOUY (en cong maternit) : julia.amkouy@ib.cegos.fr - 01 41 99 20 05 / Florence Moulin 01 41 99 20 07 -Florence.MOULIN@ib.cegos.fr",
        "contactInscription": "Suzanna PETROVIC : suzana.petrovic@ib.cegos.fr - 01 41 99 20 85",
        "remarquesContact": "<p>Mettre en copie : scc.defense@ib.cegos.fr</p>",
        "dateCreation": null,
        "dateModification": "2024-07-22",
        "repas": false,
        "positionnement": "<p>Gnraliste.</p>",
        "typeCours": "<p>Cours officiels Microsoft, VMware, Citrix, divers autres maison</p>",
        "frequenceCollaboration": "<p>Trs rgulire. CA environ 18M.</p>",
        "taillePartenaire": "<p>Grande. Prsence en Province : Lille, Lyon, Rennes, Nantes, Toulouse, Bordeaux, Aix, Strasbourg</p>",
        "qualiopiDate": null
    }
],
"formationsPartenaireAsString": "IB(1950.0)",
"filiereLibelle": "Big Data - BI - IA",
"autresFilieres": [],
"urlPlb": "/formation/aide--la-dcision/formation-intelligence-artificielle,31-700687.php",
"formationFilierePrincipale": {
    "filiere": {
        "libelle": "Big Data - BI - IA",
        "url": "aide--la-dcision",
        "description": "<p>Dans le monde en constante volution des technologies de l'information, la <strong>filire Big Data - BI - IA</strong> se dmarque comme un ple essentiel pour les professionnels cherchant  matriser les donnes massives et  prendre des dcisions claires. Cette filire rassemble une gamme varie de comptences cruciales pour naviguer dans le paysage complexe de la gestion des donnes et de la Business Intelligence (BI).</p><p>Au cur de cette filire, vous explorerez des sujets tels que l'Aide  la dcision, le logiciel R, l'architecture et les concepts du Big Data, ainsi que l'analyse et la visualisation des donnes massives. Vous dvelopperez des comptences en matire de Big Data, en utilisant des outils, des solutions et des plateformes de pointe, tout en explorant l'Intelligence Artificielle (IA) pour exploiter au maximum le potentiel des donnes.</p><p>Nous proposons galement des formations spcialises sur des technologies cls telles que Hadoop, Kafka, Talend, Business Objects, Qlik, Crystal Reports, EssBase, et bien d'autres. Vous serez en mesure de matriser ces outils essentiels pour la collecte, le traitement, l'analyse, et la visualisation des donnes.</p><p>Explorez le potentiel des donnes massives avec nous et ouvrez la porte  de nouvelles opportunits passionnantes.</p>",
        "titre": "Big Data  BI  IA (Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik)",
        "metaTitre": "Formation Big Data- BI - IA : Les cours pour apprendre le Big Data et la BI | PLB",
        "metaDescription": "Dcouvrez nos formations Big Data, BI et IA. Des cours de tous niveaux pour apprendre ou se perfectionner en formation continue avec PLB. ",
        "metaKeywords": "formation big data, formation intelligence artificielle, formation ai, formation data mining, formation webintelligence, formation hadoop, formation Data warehouse, formation Data science, formation data analyse, formation dveloppement big data, formation data analyst, formation data scientist, formation data visualisation, formation dataviz, formation reporting, formation aide  la decision, formation Talend, formation etl, formation Qlik, formation langage R, formation business objects, formation crystal reports, formation essbase, formation Kafka",
        "miniDescription": "Pour apprendre l'IA, Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik...",
        "specialiteFormation": null
    },
    "categorie": {
        "libelle": "IA (Intelligence Artificielle)",
        "filiere": {
            "libelle": "Big Data - BI - IA",
            "url": "aide--la-dcision",
            "description": "<p>Dans le monde en constante volution des technologies de l'information, la <strong>filire Big Data - BI - IA</strong> se dmarque comme un ple essentiel pour les professionnels cherchant  matriser les donnes massives et  prendre des dcisions claires. Cette filire rassemble une gamme varie de comptences cruciales pour naviguer dans le paysage complexe de la gestion des donnes et de la Business Intelligence (BI).</p><p>Au cur de cette filire, vous explorerez des sujets tels que l'Aide  la dcision, le logiciel R, l'architecture et les concepts du Big Data, ainsi que l'analyse et la visualisation des donnes massives. Vous dvelopperez des comptences en matire de Big Data, en utilisant des outils, des solutions et des plateformes de pointe, tout en explorant l'Intelligence Artificielle (IA) pour exploiter au maximum le potentiel des donnes.</p><p>Nous proposons galement des formations spcialises sur des technologies cls telles que Hadoop, Kafka, Talend, Business Objects, Qlik, Crystal Reports, EssBase, et bien d'autres. Vous serez en mesure de matriser ces outils essentiels pour la collecte, le traitement, l'analyse, et la visualisation des donnes.</p><p>Explorez le potentiel des donnes massives avec nous et ouvrez la porte  de nouvelles opportunits passionnantes.</p>",
            "titre": "Big Data  BI  IA (Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik)",
            "metaTitre": "Formation Big Data- BI - IA : Les cours pour apprendre le Big Data et la BI | PLB",
            "metaDescription": "Dcouvrez nos formations Big Data, BI et IA. Des cours de tous niveaux pour apprendre ou se perfectionner en formation continue avec PLB. ",
            "metaKeywords": "formation big data, formation intelligence artificielle, formation ai, formation data mining, formation webintelligence, formation hadoop, formation Data warehouse, formation Data science, formation data analyse, formation dveloppement big data, formation data analyst, formation data scientist, formation data visualisation, formation dataviz, formation reporting, formation aide  la decision, formation Talend, formation etl, formation Qlik, formation langage R, formation business objects, formation crystal reports, formation essbase, formation Kafka",
            "miniDescription": "Pour apprendre l'IA, Hadoop, Dataviz, Reporting, Data Warehouse, ETL, Talend, Qlik...",
            "specialiteFormation": null
        },
        "description": "<p>L<strong>Intelligence Artificielle (IA)</strong> est ne au milieu des annes 50 dans les universits amricaines. Elle connat aujourdhui un cho considrable, en particulier depuis la popularisation des IA dites  gnratives  (type <strong>ChatGPT</strong>).</p><p>Ces dernires annes ont vu apparatre des ralisations tangibles qui semblaient totalement irralisables il y a encore peu. Des programmes intelligents parviennent  supplanter lhomme dans des activits qui lui taient jusqualors rserves (reconnaissance visuelle, criture automatique darticles, vhicules sans chauffeur, ventes prdictives, robot de conversation, traduction automatique). Des pans entiers du business sont bouleverss par larrive dacteurs matrisant parfaitement ces nouveaux outils tandis que de nouvelles comptences comme le <strong>prompt engineering</strong> font surface. Toutes les industries sont trs fortement impactes par ces technologies qui dstabilisent des forces existantes.&nbsp;</p><p>Cette catgorie IA (Intelligence Artificielle) regroupe toutes nos formations IA pour vous aider  tirer pleinement profit de ces nouvelles technologies. Tournes vers la pratique, ces <strong>formations IA</strong> visent  vous rendre oprationnel et autonome quant  la matrise de ces outils en constante volution.</p><p>Nhsitez pas  contacter notre service pdagogique et technique au <strong>01 43 34 90 94</strong> si vous ne trouvez pas la formation IA que vous souhaitez.<br>Nous pouvons galement vous accompagner pour monter une session de formation IA sur mesure en vos ou en nos locaux (formation intra-entreprise)</p>",
        "metaTitre": "Formation IA (Intelligence Artificielle): la liste de nos formations | PLB Consultant",
        "metaDescription": "Cette catgorie regroupe toutes nos formations IA : IA gnrative, ChatGPT, Green IA...",
        "metaKeywords": "formation IA, formation AI, formation intelligence artificielle, formation ChatGPT, formation Green IA, formation chatbot, formation IA gnrative, formation agent conversationnel, formation reconnaissance d'image, formation prompt engineer, formation engineering",
        "categoriesConnexes": [],
        "afficherHaut": 1,
        "specialiteFormation": null
    },
    "rang": 0,
    "isPrincipale": "oui",
    "libelle": "Big Data - BI - IA",
    "principale": true
},
"excluPLB": false
}"""
tokens = encoder.encode(text)
print(f"Nombre de tokens : {len(tokens)}")
