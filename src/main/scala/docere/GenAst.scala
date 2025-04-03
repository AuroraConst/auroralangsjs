package docere

  object GenAst :
    import typings.auroraLangium.distTypesSrcLanguageGeneratedAstMod as GenAstMod
    import typings.langium.libSyntaxTreeMod.Reference
    
    type PCM                          = GenAstMod.PCM
    type Issues                       = GenAstMod.Issues
    type Orders                       = GenAstMod.Orders
    type Clinical                     = GenAstMod.Clinical
    type IssueCoordinate              = GenAstMod.IssueCoordinate
    type NGO                          = GenAstMod.NamedGroupOrder
    type NGC                          = GenAstMod.NamedGroupClinical
    type OrderCoordinate              = GenAstMod.OrderCoordinate
    type ClinicalCoordinate           = GenAstMod.ClinicalCoordinate 
    type RefCoordinateType            = GenAstMod.ReferenceCoordinate
    type LangiumReference[T]          = Reference[T]

