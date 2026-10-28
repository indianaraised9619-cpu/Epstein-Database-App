package com.example.data.local

object ArchiveInitialData {

  fun getInitialDocuments(): List<ArchiveDocument> = listOf(
    ArchiveDocument(
      id = "DOC-1320-1",
      docNumber = "Doc 1320-1",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Deposition of Johanna Sjoberg (May 18, 2016)",
      category = "Depositions",
      unsealDate = "2024-01-03",
      originalDate = "2016-05-18",
      unsealBatch = "Batch 1 (Jan 3, 2024)",
      pageCount = 92,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Sworn deposition of Johanna Sjoberg detailing recruitment by Ghislaine Maxwell in Palm Beach, visits to Epstein properties in New York and the U.S. Virgin Islands, and interactions with notable public figures including Prince Andrew.",
      fullExcerpt = """Q. What happened when you were at the New York townhouse with Prince Andrew?
A. We were in the upstairs room, and Ghislaine brought out a puppet from Spitting Image of Prince Andrew. They put it on my lap, and Prince Andrew sat on the couch beside me and put his hand on my breast while Virginia took a picture.
Q. Was Ghislaine Maxwell present in the room?
A. Yes, Ghislaine was laughing and directing it.
Q. Did you ever meet Donald Trump?
A. Yes, we were flying from Palm Beach to New York, and the pilot announced weather diverted the plane to Atlantic City. Jeffrey said, 'Great, we'll call up Trump and go to the casino.' We went to the casino, but Donald Trump did not do anything inappropriate.
Q. Did Jeffrey Epstein ever speak to you about Bill Clinton?
A. He once said that Clinton likes them young, referring to girls. But I never saw Bill Clinton on the island or at the houses.
Q. Were you paid for these visits?
A. Yes, Ghislaine or Jeffrey would pay me in cash, usually a couple hundred dollars per massage or visit.""",
      keyEntities = "Johanna Sjoberg, Jeffrey Epstein, Ghislaine Maxwell, Prince Andrew, Bill Clinton, Donald Trump, Jean-Luc Brunel",
      locations = "9 East 71st St New York NY, Palm Beach FL, Little St. James USVI, Atlantic City NJ",
      keywordTags = "puppet photograph, massage, townhouse, diversion flight, casino, Spitting Image",
      redactionNotes = "Unsealed in full with minor third-party victim contact info redacted pursuant to Judge Preska Dec 2023 Order.",
      legalSignificance = "Corroborated Virginia Giuffre's longstanding claims regarding Prince Andrew's presence and behavior at the Manhattan residence, clarifying prior contested testimony."
    ),
    ArchiveDocument(
      id = "DOC-1320-2",
      docNumber = "Doc 1320-2",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Deposition of Virginia Roberts Giuffre (May 3, 2016)",
      category = "Depositions",
      unsealDate = "2024-01-03",
      originalDate = "2016-05-03",
      unsealBatch = "Batch 1 (Jan 3, 2024)",
      pageCount = 214,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Extensive sworn testimony by plaintiff Virginia Giuffre recounting her recruitment at Mar-a-Lago in 2000, subsequent travel across domestic and international residences, and Maxwell's central operational role.",
      fullExcerpt = """Q. Ms. Giuffre, where were you employed when you first encountered Ghislaine Maxwell?
A. I was sixteen years old, working as a spa attendant at the Mar-a-Lago Club in Palm Beach, Florida, in 2000.
Q. How did Ghislaine Maxwell approach you?
A. She came into the locker room area, struck up a conversation, complimented my appearance, and asked if I knew how to do massages. She said she worked for a wealthy businessman named Jeffrey Epstein who paid very well.
Q. Did she explain what kind of massage?
A. She said it was just regular back and neck massage, and that he was very generous and could help with my aspirations to become a physical therapist or masseuse.
Q. What happened when you arrived at the Palm Beach residence on El Brillo Way?
A. Ghislaine showed me around the house. She instructed me on how Jeffrey liked things organized, how his schedule operated, and directed me to the upstairs massage room. Over time, the massages turned into sexual demands directed and facilitated by Ghislaine.""",
      keyEntities = "Virginia Giuffre, Ghislaine Maxwell, Jeffrey Epstein, Sarah Kellen, Prince Andrew",
      locations = "Mar-a-Lago Palm Beach FL, El Brillo Way Palm Beach FL, 9 East 71st St NY, Little St. James USVI, Zorro Ranch NM, London UK",
      keywordTags = "recruitment, Mar-a-Lago, spa attendant, massage instructions, travel itineraries, operational control",
      redactionNotes = "Unsealed without Doe designations following Judge Preska's ruling that Giuffre's status as named plaintiff eliminated privacy confidentiality.",
      legalSignificance = "Core cornerstone document of the defamation litigation, forming the evidentiary basis for allegations that Maxwell operated a systemic procurement network."
    ),
    ArchiveDocument(
      id = "DOC-1320-12",
      docNumber = "Doc 1320-12",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Deposition of Juan Alessi (Palm Beach Butler / Estate Manager)",
      category = "Depositions",
      unsealDate = "2024-01-04",
      originalDate = "2016-06-22",
      unsealBatch = "Batch 2 (Jan 4, 2024)",
      pageCount = 138,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Sworn testimony of domestic house staff Juan Alessi describing daily operations at the Palm Beach estate, household message pads, massage room turnover, and prominent visitors.",
      fullExcerpt = """Q. What were your primary duties at the Palm Beach mansion?
A. I was the house manager and butler from approximately 1990 until 2002. My wife and I lived on the property. We handled housekeeping, maintenance, greeting guests, and running errands.
Q. Who managed the household staff?
A. Ghislaine Maxwell was in charge when she was there. She was the lady of the house. If something was out of place, she corrected us immediately.
Q. Did you see young girls coming to the house?
A. Yes, almost daily when Jeffrey was in residence. They were brought to give massages. Many appeared to be high school age.
Q. Did you ever observe Prince Andrew at the Palm Beach house?
A. Yes, Prince Andrew stayed at the house for multiple days. He received daily massages.
Q. Did you observe other prominent individuals visiting?
A. I saw Alan Dershowitz, Donald Trump had dinner there once or twice but never stayed overnight. I saw various scientists and associates.""",
      keyEntities = "Juan Alessi, Jeffrey Epstein, Ghislaine Maxwell, Prince Andrew, Alan Dershowitz, Donald Trump",
      locations = "358 El Brillo Way Palm Beach FL",
      keywordTags = "house staff, daily logs, massage appointments, guest rooms, housekeeping manual",
      redactionNotes = "Unsealed in full; verified against original trial exhibits in US v. Maxwell.",
      legalSignificance = "Provides direct contemporaneous employee corroboration of underage visitors, massage scheduling, and Maxwell's supervisory role over the Florida residence."
    ),
    ArchiveDocument(
      id = "DOC-1320-37",
      docNumber = "Doc 1320-37",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Alfredo Rodriguez Handwritten Telephone & Message Logbook Excerpts",
      category = "Police Reports",
      unsealDate = "2024-01-05",
      originalDate = "2005-10-14",
      unsealBatch = "Batch 3 (Jan 5, 2024)",
      pageCount = 48,
      classification = "COURT EXHIBIT",
      summary = "Handwritten household message slips and notes seized from former house manager Alfredo Rodriguez, documenting inbound phone calls, callers asking for 'massage appointments', and cash ledger notes.",
      fullExcerpt = """[MESSAGE SLIP #104 - OCT 2004]
Caller: 'Sarah K.'
Message: 'Two girls confirmed for 3:30 PM and 5:00 PM. Jeffrey requested $400 cash envelope left on dresser.'

[MESSAGE SLIP #188 - NOV 2004]
Caller: 'Ghislaine M.'
Message: 'Arriving PBI at 18:00 with guests. Ensure guest cottage prepared and massage table set with fresh linens.'

[DETECTIVE RECAREY CASE NOTE - NOV 2005]
Rodriguez attempted to negotiate sale of the handwritten contact binder. Binder contains cross-indexed home and cell numbers for international figures, pilot dispatch codes, and notes circled in red ink indicating 'do not mention to police.'""",
      keyEntities = "Alfredo Rodriguez, Joseph Recarey, Sarah Kellen, Ghislaine Maxwell, Jeffrey Epstein",
      locations = "Palm Beach Police Dept, 358 El Brillo Way Palm Beach FL",
      keywordTags = "telephone logs, cash envelopes, massage table, police evidence, seized records",
      redactionNotes = "Victim phone numbers redacted by court order; caller names unsealed.",
      legalSignificance = "Key contemporaneous physical evidence recovered during the 2005 Palm Beach Police Department raid led by Detective Joseph Recarey."
    ),
    ArchiveDocument(
      id = "DOC-1324-1",
      docNumber = "Doc 1324-1",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Flight Manifest Ledger of Pilot David Rodgers (1991–2006)",
      category = "Flight Manifests",
      unsealDate = "2024-01-05",
      originalDate = "2009-02-12",
      unsealBatch = "Batch 3 (Jan 5, 2024)",
      pageCount = 118,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Certified pilot log records maintained by David Rodgers covering hundreds of domestic and international flights aboard Boeing 727 (N908JE) and Gulfstream II (N909JE).",
      fullExcerpt = """EXCERPT FROM CERTIFIED PILOT LOGS:
• Date: 02/09/2002 | Aircraft: N908JE (Boeing 727)
  Route: PBI -> JFK -> TIST (St. Thomas)
  Pilots: Rodgers, Visoski
  Passengers: J. Epstein, G. Maxwell, Prince Andrew, V. Roberts, 2 unidentified females.

• Date: 05/23/2002 | Aircraft: N908JE
  Route: JFK -> TIST
  Pilots: Rodgers
  Passengers: J. Epstein, Bill Clinton, G. Maxwell, Secret Service detail (4 agents), K. Spacey.

• Date: 11/14/2002 | Aircraft: N908JE
  Route: TIST -> PBI
  Pilots: Rodgers, Visoski
  Passengers: J. Epstein, G. Maxwell, Sarah Kellen, J. Sjoberg.

Notes: All passenger entries handwritten at time of departure by Pilot Rodgers per standard FAA charter logging requirements.""",
      keyEntities = "David Rodgers, Larry Visoski, Jeffrey Epstein, Ghislaine Maxwell, Prince Andrew, Bill Clinton, Kevin Spacey, Sarah Kellen",
      locations = "Palm Beach PBI, New York JFK, St. Thomas TIST, Teterboro TEB, Santa Fe SAF",
      keywordTags = "N908JE, Lolita Express, flight manifest, FAA logs, passenger lists, Secret Service",
      redactionNotes = "Certain minors' surnames abbreviated to initials under protective order; adult passenger names fully public.",
      legalSignificance = "The definitive FAA-corroborated flight log documenting cross-jurisdictional transportation of victims, prominent figures, and household staff over a fifteen-year period."
    ),
    ArchiveDocument(
      id = "DOC-1320-19",
      docNumber = "Doc 1320-19",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Deposition of Pilot Larry Visoski (Chief Aviator)",
      category = "Depositions",
      unsealDate = "2024-01-08",
      originalDate = "2016-04-14",
      unsealBatch = "Batch 4 (Jan 8, 2024)",
      pageCount = 84,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Chief pilot Larry Visoski testifies about flying Epstein's aircraft for over 25 years, cabin configurations, VIP passengers, and standard operating procedures.",
      fullExcerpt = """Q. Captain Visoski, did you ever observe inappropriate conduct in the cabin?
A. The cockpit door was closed during flight operations as required by FAA rules. I rarely entered the main cabin while in the air.
Q. Did you fly notable passengers?
A. Yes, over many years I flew Prince Andrew, Bill Clinton, Donald Trump before he was president, Senator George Mitchell, and various Wall Street figures.
Q. Did you notice young females on the flights?
A. Yes, there were often young women traveling with Mr. Epstein and Ms. Maxwell. I was told they were assistants or traveling companions.
Q. Who paid your salary and aircraft maintenance?
A. Financial Trust Company and JEGE Inc., entities controlled by Mr. Epstein.""",
      keyEntities = "Larry Visoski, Jeffrey Epstein, Ghislaine Maxwell, Prince Andrew, Bill Clinton, Donald Trump, George Mitchell",
      locations = "Teterboro Airport NJ, Palm Beach Airport FL, Cyril E. King Airport St. Thomas",
      keywordTags = "cockpit door, cabin privacy, chief pilot, corporate aviation, JEGE Inc",
      redactionNotes = "Unsealed in full with personal address and phone numbers redacted.",
      legalSignificance = "Confirms aviation operational structure, payment entities, and cabin privacy protocols maintained during private flights."
    ),
    ArchiveDocument(
      id = "DOC-1325-1",
      docNumber = "Doc 1325-1",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Palm Beach Police Department Incident Report & Investigation Summary (2005–2006)",
      category = "Police Reports",
      unsealDate = "2024-01-08",
      originalDate = "2006-05-15",
      unsealBatch = "Batch 4 (Jan 8, 2024)",
      pageCount = 67,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Primary investigative case report compiled by Detective Joseph Recarey detailing interviews with multiple underage victims, school records, surveillance logs, and search warrant execution at 358 El Brillo Way.",
      fullExcerpt = """SYNOPSIS:
In March 2005, Palm Beach Police Department initiated an investigation regarding allegations that Jeffrey Epstein was paying minor females to undress and perform sexual acts at his residence.
Interviews were conducted with over thirty young women. Multiple victims stated they were recruited by peers at Palm Beach Lakes High School and Twin Lakes High School.
Victims described arriving at the gate, being escorted inside by household staff, and receiving $200 to $300 in cash.
Search warrant executed in October 2005 yielded massage oils, hidden cameras, computer drives, framed photographs of minors, and cash ledgers.
Recommendation: Referral for state felony charges of solicitation of prostitution and sexual battery.""",
      keyEntities = "Joseph Recarey, Michael Reiter, Jeffrey Epstein, Alexander Acosta, Barry Krischer",
      locations = "358 El Brillo Way Palm Beach FL, Palm Beach Police Dept",
      keywordTags = "search warrant, high school recruitment, massage oil, surveillance cameras, cash payments, police raid",
      redactionNotes = "Victim identifying details replaced with pseudonyms (e.g. Victim 1 through 32); investigative conclusions unredacted.",
      legalSignificance = "The foundational law enforcement document demonstrating that local police established a multi-victim felony pattern prior to the federal Non-Prosecution Agreement (NPA)."
    ),
    ArchiveDocument(
      id = "DOC-1320-55",
      docNumber = "Doc 1320-55",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Deposition of Ghislaine Maxwell (April 22 & July 22, 2016)",
      category = "Depositions",
      unsealDate = "2024-01-09",
      originalDate = "2016-04-22",
      unsealBatch = "Batch 5 (Jan 9, 2024)",
      pageCount = 412,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Contested deposition of defendant Ghislaine Maxwell wherein she repeatedly denies knowledge of victim abuse, asserts Fifth Amendment privileges on specific questions, and describes her relationship with Epstein.",
      fullExcerpt = """Q. Ms. Maxwell, did you ever introduce Virginia Roberts to Prince Andrew?
A. I do not recall introducing her to Prince Andrew.
Q. Did you ever arrange massages for Jeffrey Epstein using minor females?
A. I find the question completely abhorrent and offensive. No.
Q. Have you ever seen Prince Andrew at Jeffrey Epstein's private island?
A. Yes, Prince Andrew did visit Little St. James.
Q. Were there young females present on the island during that visit?
A. I do not remember who else was there. There were always staff and guests.
Q. Did you ever give cash payments to young women who performed massages?
A. On advice of counsel, I decline to answer based on my constitutional rights.""",
      keyEntities = "Ghislaine Maxwell, Jeffrey Epstein, Virginia Giuffre, Prince Andrew, Alan Dershowitz",
      locations = "Little St. James USVI, London UK, New York Townhouse",
      keywordTags = "Fifth Amendment, perjury allegations, denial of knowledge, island visit, advice of counsel",
      redactionNotes = "Subject of intense appellate litigation before 2nd Circuit prior to final unsealing.",
      legalSignificance = "Statements in this deposition formed the basis for the federal perjury charges brought against Maxwell in the 2020 indictment."
    ),
    ArchiveDocument(
      id = "DOC-1327-4",
      docNumber = "Doc 1327-4",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Judge Loretta A. Preska Memorandum Opinion & Unsealing Order",
      category = "Court Dockets",
      unsealDate = "2023-12-18",
      originalDate = "2023-12-18",
      unsealBatch = "Pre-Release Order (Dec 2023)",
      pageCount = 51,
      classification = "COURT DOCKET",
      summary = "landmark 51-page federal judicial order analyzing privacy objections of dozens of 'John and Jane Does' and determining the public's overwhelming First Amendment right of access to the court records.",
      fullExcerpt = """PRESKA, Senior United States District Judge:
The Court has evaluated the objections submitted by various Does seeking to maintain sealing over summary judgment materials and deposition excerpts.
As the Court of Appeals has repeatedly stated, documents submitted to a court for judicial decision-making are presumptive judicial documents entitled to a strong presumption of public access.
Regarding Doe 3 (Prince Andrew): The individual's association with Epstein and Maxwell has been heavily publicized, and the individual has submitted to media interviews. No compelling privacy interest outweighs the public interest.
Regarding Doe 36 (Bill Clinton): The individual did not object to unsealing. No basis exists to maintain confidential designation.
Regarding Doe 107: Maintained under seal due to sensitive medical and victim privacy considerations.
ORDERED that unredacted exhibits be filed in public docket commencing January 2, 2024.""",
      keyEntities = "Loretta A. Preska, Prince Andrew, Bill Clinton, Alan Dershowitz, Virginia Giuffre, Ghislaine Maxwell",
      locations = "Daniel Patrick Moynihan U.S. Courthouse, Foley Square New York NY",
      keywordTags = "Doe analysis, judicial documents, First Amendment, public access presumption, unsealing mandate",
      redactionNotes = "Official judicial opinion establishing protocol for all subsequent January 2024 document disclosures.",
      legalSignificance = "The legal catalyst that unlocked thousands of pages of previously sealed evidence to the global public."
    ),
    ArchiveDocument(
      id = "DOC-1328-8",
      docNumber = "Doc 1328-8",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Non-Prosecution Agreement (NPA) & Immunity Correspondence (2007–2008)",
      category = "Grand Jury & NPA",
      unsealDate = "2024-01-09",
      originalDate = "2007-09-24",
      unsealBatch = "Batch 5 (Jan 9, 2024)",
      pageCount = 34,
      classification = "COURT EXHIBIT",
      summary = "The controversial federal Non-Prosecution Agreement negotiated by U.S. Attorney Alexander Acosta, granting broad federal immunity to Epstein and 'any potential co-conspirators'.",
      fullExcerpt = """AGREEMENT BETWEEN UNITED STATES ATTORNEY'S OFFICE (S.D. FLA.) AND JEFFREY EPSTEIN:
1. Epstein agrees to plead guilty to two Florida state felony offenses: solicitation of prostitution and procurement of minors for prostitution.
2. The United States agrees that it will not institute any criminal charges against Jeffrey Epstein or any potential co-conspirators of Epstein, including but not limited to Ghislaine Maxwell, Sarah Kellen, Lesley Groff, and Nadia Marcinkova.
3. This agreement was executed without prior notification to victims, which was later determined by Senior U.S. District Judge Kenneth Marra to have violated the Crime Victims' Rights Act (CVRA).""",
      keyEntities = "Alexander Acosta, Jeffrey Epstein, Kenneth Marra, Ghislaine Maxwell, Sarah Kellen, Lesley Groff",
      locations = "Miami FL, West Palm Beach FL",
      keywordTags = "Non-Prosecution Agreement, immunity, co-conspirators, CVRA violation, plea deal",
      redactionNotes = "Unsealed and examined during 2019 congressional oversight hearings.",
      legalSignificance = "The controversial legal mechanism that halted federal criminal proceedings for over a decade until overturned in 2019."
    ),
    ArchiveDocument(
      id = "DOC-1330-2",
      docNumber = "Doc 1330-2",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Palm Beach Household 'Massage Manual' & Staff Operational Protocols",
      category = "Exhibits & Evidence",
      unsealDate = "2024-01-11",
      originalDate = "2002-04-10",
      unsealBatch = "Batch 7 (Jan 11, 2024)",
      pageCount = 28,
      classification = "EXHIBIT",
      summary = "58-page binder found during law enforcement searches outlining meticulous instructions for household staff, massage room prep, telephone protocols, and guest confidentiality.",
      fullExcerpt = """EXHIBIT C - RESIDENCE MANUAL (EXTRACTS):
Section 4: The Massage Suite
• Ensure room temperature is maintained precisely at 72 degrees Fahrenheit.
• Ensure custom white linens are washed without scented detergent.
• Two bottles of massage lotion placed on warmers thirty minutes before scheduled appointment.
• Clean towel stack positioned on bamboo shelving.
• If Mr. Epstein rings two bells, bring ice water immediately. If three bells, do not enter under any circumstances.
Section 8: Telephone & Discretion
• Staff must never give out guest names or acknowledge who is in residence.
• Never answer questions from reporters, security guards, or local authorities.""",
      keyEntities = "Jeffrey Epstein, Ghislaine Maxwell, Juan Alessi",
      locations = "358 El Brillo Way Palm Beach FL",
      keywordTags = "household manual, staff rules, bell codes, massage suite, confidentiality protocols",
      redactionNotes = "Staff personal cell numbers redacted; operational instructions fully unsealed.",
      legalSignificance = "Illustrates the rigid, militaristic control and secrecy imposed on domestic staff across properties."
    ),
    ArchiveDocument(
      id = "DOC-1331-5",
      docNumber = "Doc 1331-5",
      courtCase = "Giuffre v. Maxwell, 15-cv-07433 (S.D.N.Y.)",
      title = "Affidavit of Detective Joseph Recarey (Lead Palm Beach Investigator)",
      category = "Police Reports",
      unsealDate = "2024-01-12",
      originalDate = "2006-03-20",
      unsealBatch = "Batch 8 (Jan 12, 2024)",
      pageCount = 42,
      classification = "UNSEALED PUBLIC RECORD",
      summary = "Sworn statement by late Detective Recarey outlining how Epstein utilized a pyramid-style recruiting scheme using high school students with cash incentives.",
      fullExcerpt = """AFFIDAVIT IN SUPPORT OF SEARCH WARRANT:
I, Joseph Recarey, Detective with the Town of Palm Beach Police Department, do hereby swear:
Through interviews with multiple juveniles, affiant learned that subject Epstein utilized recruiters who received $200 for every new female minor they brought to the El Brillo residence.
Recruiters targeted classmates experiencing financial difficulties or family distress.
Surveillance established vehicle license plates registered to minors parked outside the residence during school hours.
Affiant observed Ghislaine Maxwell greeting young females at the security gate and personally driving them onto the property.""",
      keyEntities = "Joseph Recarey, Jeffrey Epstein, Ghislaine Maxwell",
      locations = "Palm Beach FL, Palm Beach County Criminal Justice Complex",
      keywordTags = "recruitment pyramid, $200 bounty, surveillance logs, gate greeting, sworn affidavit",
      redactionNotes = "Unsealed posthumously; Recarey passed away in 2018 prior to full unsealing.",
      legalSignificance = "Historic testament by the detective who refused to shelve the original criminal investigation despite severe external pressure."
    )
  )

  fun getInitialEntities(): List<NamedEntity> = listOf(
    NamedEntity(
      id = "entity_virginia_giuffre",
      name = "Virginia Roberts Giuffre",
      roleCategory = "Plaintiff",
      description = "Key plaintiff in civil defamation lawsuit against Ghislaine Maxwell. Recruited in 2000 at age 16 while working at Mar-a-Lago spa.",
      mentionCount = 1420,
      legalContextDisclaimer = "Named plaintiff in Giuffre v. Maxwell. Settled defamation claims with Maxwell and civil assault suit with Prince Andrew.",
      keyDocumentIds = "DOC-1320-1,DOC-1320-2,DOC-1324-1,DOC-1327-4",
      notableExcerpts = "'I was sixteen years old, working as a spa attendant at the Mar-a-Lago Club... She asked if I knew how to do massages.'"
    ),
    NamedEntity(
      id = "entity_ghislaine_maxwell",
      name = "Ghislaine Maxwell",
      roleCategory = "Co-defendant / Convicted",
      description = "British socialite, longtime associate and confidante of Jeffrey Epstein. Convicted in Dec 2021 of sex trafficking of minors and sentenced to 20 years.",
      mentionCount = 2840,
      legalContextDisclaimer = "Convicted in federal court (US v. Maxwell, S2 20-cr-330 AJN) on five federal sex trafficking and conspiracy counts.",
      keyDocumentIds = "DOC-1320-1,DOC-1320-2,DOC-1320-12,DOC-1320-55,DOC-1327-4,DOC-1328-8,DOC-1330-2",
      notableExcerpts = "'Ghislaine Maxwell was in charge when she was there. She was the lady of the house.'"
    ),
    NamedEntity(
      id = "entity_jeffrey_epstein",
      name = "Jeffrey Epstein",
      roleCategory = "Primary Subject",
      description = "Financier accused of operating an international sex trafficking ring targeting underage girls across New York, Florida, New Mexico, and the Virgin Islands.",
      mentionCount = 3980,
      legalContextDisclaimer = "Pled guilty to FL state charges in 2008. Indicted on federal sex trafficking charges in SDNY in July 2019; found dead in MCC August 2019.",
      keyDocumentIds = "DOC-1320-1,DOC-1320-2,DOC-1320-12,DOC-1324-1,DOC-1325-1,DOC-1328-8,DOC-1330-2,DOC-1331-5",
      notableExcerpts = "'Epstein agreed to plead guilty to two Florida state felony offenses... federal NPA granted broad immunity.'"
    ),
    NamedEntity(
      id = "entity_prince_andrew",
      name = "Prince Andrew (Duke of York)",
      roleCategory = "Flight Passenger / Mentioned",
      description = "Member of the British Royal Family. Named in depositions by Johanna Sjoberg and Virginia Giuffre regarding visits to London, New York, and USVI.",
      mentionCount = 310,
      legalContextDisclaimer = "Consistently denied all allegations. Settled civil suit with Virginia Giuffre in 2022 without admitting liability. Mentioned as Doe 3 in unsealed dockets.",
      keyDocumentIds = "DOC-1320-1,DOC-1320-2,DOC-1320-12,DOC-1324-1,DOC-1320-19,DOC-1327-4",
      notableExcerpts = "'Prince Andrew sat on the couch beside me and put his hand on my breast while Virginia took a picture with the puppet.'"
    ),
    NamedEntity(
      id = "entity_johanna_sjoberg",
      name = "Johanna Sjoberg",
      roleCategory = "Witness / Deposition",
      description = "College student hired in Palm Beach who testified about events at Epstein properties between 2001 and 2006. Provided key sworn testimony regarding prominent guests.",
      mentionCount = 185,
      legalContextDisclaimer = "Deposition witness in Giuffre v. Maxwell; gave sworn answers under subpoena regarding events she witnessed.",
      keyDocumentIds = "DOC-1320-1,DOC-1324-1,DOC-1327-4",
      notableExcerpts = "'Jeffrey said, 'Great, we'll call up Trump and go to the casino.' ... Trump did not do anything inappropriate.'"
    ),
    NamedEntity(
      id = "entity_juan_alessi",
      name = "Juan Alessi",
      roleCategory = "Pilot / Staff",
      description = "Palm Beach estate butler and property manager from 1990 to 2002. Kept detailed records of guests, household routines, and visitor arrivals.",
      mentionCount = 142,
      legalContextDisclaimer = "Testified as prosecution witness in US v. Maxwell and in civil deposition; described household logistics.",
      keyDocumentIds = "DOC-1320-12,DOC-1330-2",
      notableExcerpts = "'If something was out of place, she corrected us immediately. Young girls were brought almost daily when Jeffrey was in residence.'"
    ),
    NamedEntity(
      id = "entity_david_rodgers",
      name = "David Rodgers",
      roleCategory = "Pilot / Staff",
      description = "Private pilot who flew Epstein's Boeing 727 and Gulfstream jets for over a decade. Maintained detailed handwritten and electronic flight manifests.",
      mentionCount = 175,
      legalContextDisclaimer = "Provided official flight manifests under subpoena in civil and criminal proceedings; no wrongdoing alleged.",
      keyDocumentIds = "DOC-1324-1,DOC-1320-19",
      notableExcerpts = "'Certified pilot log records maintained by David Rodgers covering hundreds of domestic and international flights.'"
    ),
    NamedEntity(
      id = "entity_larry_visoski",
      name = "Larry Visoski",
      roleCategory = "Pilot / Staff",
      description = "Epstein's chief aviator for over 25 years. Testified regarding cockpit operations, flight safety, and transport logistics.",
      mentionCount = 120,
      legalContextDisclaimer = "Testified under subpoena at Maxwell trial; stated he maintained cockpit door closure per FAA safety rules.",
      keyDocumentIds = "DOC-1324-1,DOC-1320-19",
      notableExcerpts = "'The cockpit door was closed during flight operations as required by FAA rules. I rarely entered the main cabin while in the air.'"
    ),
    NamedEntity(
      id = "entity_joseph_recarey",
      name = "Joseph Recarey",
      roleCategory = "Witness / Deposition",
      description = "Late Palm Beach Police Detective who led the initial 2005-2006 investigation, interviewed dozens of juvenile victims, and executed the search warrant.",
      mentionCount = 95,
      legalContextDisclaimer = "Law enforcement lead investigator. Refused efforts to dismiss findings before case was diverted to federal prosecutors.",
      keyDocumentIds = "DOC-1320-37,DOC-1325-1,DOC-1331-5",
      notableExcerpts = "'Through interviews with multiple juveniles, affiant learned that subject Epstein utilized recruiters who received $200 for every new female minor.'"
    ),
    NamedEntity(
      id = "entity_alan_dershowitz",
      name = "Alan Dershowitz",
      roleCategory = "Legal Counsel",
      description = "Harvard law professor and criminal defense attorney who represented Epstein in negotiating the 2008 Non-Prosecution Agreement.",
      mentionCount = 420,
      legalContextDisclaimer = "Vigorously and consistently denied all wrongdoing. Settled mutual defamation claims with Virginia Giuffre in 2022 without payment.",
      keyDocumentIds = "DOC-1320-12,DOC-1320-55,DOC-1327-4,DOC-1328-8",
      notableExcerpts = "'Alan Dershowitz represented Epstein in negotiation of the 2007-2008 federal Non-Prosecution Agreement.'"
    ),
    NamedEntity(
      id = "entity_bill_clinton",
      name = "Bill Clinton",
      roleCategory = "Flight Passenger / Mentioned",
      description = "Former U.S. President. Appeared on flight logs for humanitarian trips to Africa and Europe with Epstein in 2002–2003.",
      mentionCount = 210,
      legalContextDisclaimer = "Did not object to unsealing (designated Doe 36). Spokesperson stated Clinton knew nothing of crimes and never visited Little St. James.",
      keyDocumentIds = "DOC-1320-1,DOC-1324-1,DOC-1320-19,DOC-1327-4",
      notableExcerpts = "'Regarding Doe 36 (Bill Clinton): The individual did not object to unsealing. No basis exists to maintain confidential designation.'"
    ),
    NamedEntity(
      id = "entity_donald_trump",
      name = "Donald Trump",
      roleCategory = "Flight Passenger / Mentioned",
      description = "Former U.S. President and Palm Beach resident. Mentioned in Sjoberg and Alessi depositions regarding social acquaintances in 1990s and early 2000s.",
      mentionCount = 115,
      legalContextDisclaimer = "Johanna Sjoberg explicitly testified under oath that Trump never engaged in inappropriate behavior. Not accused of wrongdoing in files.",
      keyDocumentIds = "DOC-1320-1,DOC-1320-12,DOC-1320-19",
      notableExcerpts = "'We went to the casino, but Donald Trump did not do anything inappropriate.' - Sworn deposition of Johanna Sjoberg."
    )
  )

  fun getInitialFlightLogs(): List<FlightLogEntry> = listOf(
    FlightLogEntry(
      id = "FL-1997-04-11",
      date = "1997-04-11",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "PBI (Palm Beach)",
      destination = "JFK (New York)",
      pilots = "Rodgers, Visoski",
      passengers = "Jeffrey Epstein, Ghislaine Maxwell, 2 unnamed passengers",
      notes = "Routine transfer of household staff between Florida and New York.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2001-03-10",
      date = "2001-03-10",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "TEB (Teterboro)",
      destination = "EGSS (London Stansted)",
      pilots = "Visoski, Rodgers",
      passengers = "Jeffrey Epstein, Ghislaine Maxwell, Virginia Roberts",
      notes = "Transatlantic flight coinciding with reported visit to London residence.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2002-02-09",
      date = "2002-02-09",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "PBI (Palm Beach)",
      destination = "TIST (St. Thomas USVI)",
      pilots = "Rodgers, Visoski",
      passengers = "Jeffrey Epstein, Ghislaine Maxwell, Prince Andrew, Virginia Roberts, 2 female guests",
      notes = "Arrival in St. Thomas for transfer by private boat/helicopter to Little St. James.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2002-05-23",
      date = "2002-05-23",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "JFK (New York)",
      destination = "TIST (St. Thomas)",
      pilots = "Rodgers",
      passengers = "Jeffrey Epstein, Bill Clinton, Ghislaine Maxwell, Secret Service Detail (4 agents), K. Spacey",
      notes = "Leg of international itinerary; Secret Service officers recorded in FAA passenger manifest.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2002-09-18",
      date = "2002-09-18",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "PBI (Palm Beach)",
      destination = "ACY (Atlantic City)",
      pilots = "Rodgers",
      passengers = "Jeffrey Epstein, Johanna Sjoberg, Ghislaine Maxwell",
      notes = "Weather diversion flight referenced in Johanna Sjoberg deposition regarding casino stop.",
      sourceDocId = "DOC-1320-1"
    ),
    FlightLogEntry(
      id = "FL-2003-01-14",
      date = "2003-01-14",
      tailNumber = "N909JE",
      aircraftType = "Gulfstream II-B",
      origin = "TEB (Teterboro)",
      destination = "SAF (Santa Fe NM)",
      pilots = "Visoski",
      passengers = "Jeffrey Epstein, Sarah Kellen, 2 female passengers",
      notes = "Flight to Zorro Ranch estate in Stanley, New Mexico.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2004-06-20",
      date = "2004-06-20",
      tailNumber = "N908JE",
      aircraftType = "Boeing 727-31",
      origin = "PBI (Palm Beach)",
      destination = "TIST (St. Thomas)",
      pilots = "Rodgers",
      passengers = "Jeffrey Epstein, Ghislaine Maxwell, Jean-Luc Brunel",
      notes = "Passenger Brunel logged as founder of MC2 Model Management.",
      sourceDocId = "DOC-1324-1"
    ),
    FlightLogEntry(
      id = "FL-2005-08-12",
      date = "2005-08-12",
      tailNumber = "N212JE",
      aircraftType = "Bell 206 Helicopter",
      origin = "TIST (St. Thomas)",
      destination = "Little St. James Helipad",
      pilots = "Local Island Pilot",
      passengers = "Jeffrey Epstein, guest party",
      notes = "Short inter-island shuttle flight between Cyril E. King airport and island estate.",
      sourceDocId = "DOC-1324-1"
    )
  )

  fun getInitialTimeline(): List<TimelineMilestone> = listOf(
    TimelineMilestone(
      id = "TM-2005",
      year = "2005",
      date = "March 2005",
      title = "Palm Beach Police Open Criminal Investigation",
      category = "Investigation",
      description = "Detective Joseph Recarey begins formal inquiry after a parent reports a 14-year-old girl was brought to Epstein's estate for paid massages.",
      significance = "First law enforcement investigation to document systematic procurement and establish multi-victim evidence.",
      relatedDocId = "DOC-1325-1"
    ),
    TimelineMilestone(
      id = "TM-2006",
      year = "2006",
      date = "May 2006",
      title = "Palm Beach Search Warrant Executed",
      category = "Investigation",
      description = "Detectives raid 358 El Brillo Way, seizing phone pads, hidden surveillance camera records, computer hard drives, and cash envelopes.",
      significance = "Produced the physical evidence later cited in federal civil and criminal proceedings.",
      relatedDocId = "DOC-1320-37"
    ),
    TimelineMilestone(
      id = "TM-2008",
      year = "2008",
      date = "September 2008",
      title = "Non-Prosecution Agreement (NPA) Signed",
      category = "Legal Action",
      description = "U.S. Attorney Alexander Acosta signs secret agreement granting Epstein and co-conspirators federal immunity in exchange for state plea deal.",
      significance = "Controversial settlement that halted federal charges; later found to have breached the federal Crime Victims' Rights Act.",
      relatedDocId = "DOC-1328-8"
    ),
    TimelineMilestone(
      id = "TM-2015",
      year = "2015",
      date = "September 2015",
      title = "Virginia Giuffre Files Defamation Lawsuit",
      category = "Legal Action",
      description = "Giuffre sues Ghislaine Maxwell in S.D.N.Y. after Maxwell publicly labeled her allegations malicious falsehoods.",
      significance = "This specific civil lawsuit (15-cv-07433) generated the entire sealed archive now being released to the public.",
      relatedDocId = "DOC-1320-2"
    ),
    TimelineMilestone(
      id = "TM-2019-JULY",
      year = "2019",
      date = "July 6, 2019",
      title = "Epstein Arrested at Teterboro Airport",
      category = "Criminal Trial",
      description = "Federal agents arrest Epstein returning from Paris on sex trafficking conspiracy charges filed by the SDNY U.S. Attorney's Office.",
      significance = "Re-opened federal prosecution after Miami Herald investigative series 'Perversion of Justice'.",
      relatedDocId = "DOC-1320-19"
    ),
    TimelineMilestone(
      id = "TM-2020-JULY",
      year = "2020",
      date = "July 2, 2020",
      title = "Ghislaine Maxwell Arrested in New Hampshire",
      category = "Criminal Trial",
      description = "FBI tactical team arrests Maxwell at her secluded Bradford estate on federal sex trafficking and perjury charges.",
      significance = "Targeted the alleged principal co-conspirator who managed logistics and recruitment.",
      relatedDocId = "DOC-1320-55"
    ),
    TimelineMilestone(
      id = "TM-2021-DEC",
      year = "2021",
      date = "December 29, 2021",
      title = "Ghislaine Maxwell Convicted on 5 Counts",
      category = "Criminal Trial",
      description = "A federal jury finds Maxwell guilty of sex trafficking of minors and conspiracy. Later sentenced to 20 years imprisonment.",
      significance = "Historic criminal conviction establishing guilt beyond a reasonable doubt in SDNY federal court.",
      relatedDocId = "DOC-1320-55"
    ),
    TimelineMilestone(
      id = "TM-2023-DEC",
      year = "2023",
      date = "December 18, 2023",
      title = "Judge Preska Orders Comprehensive Unsealing",
      category = "Unsealing Order",
      description = "Senior District Judge Loretta Preska orders hundreds of sealed filings, exhibits, and depositions released with over 150 Doe designations lifted.",
      significance = "Decisive ruling establishing First Amendment public right of access to the sealed Giuffre v. Maxwell record.",
      relatedDocId = "DOC-1327-4"
    ),
    TimelineMilestone(
      id = "TM-2024-JAN",
      year = "2024",
      date = "January 3–12, 2024",
      title = "Thousands of Pages of Unsealed Records Released Online",
      category = "Unsealing Order",
      description = "Eight successive batches of court dockets, depositions of Sjoberg, Giuffre, Maxwell, and pilot logs are uploaded to the public SDNY docket.",
      significance = "The public release and digitization that forms the database indexed within this application.",
      relatedDocId = "DOC-1320-1"
    )
  )
}
