import {dsl} from "@/lib/fetch/graphql/dsl/utils";


const metadata = "/_metadata"


export const PROPERTIES_CLASSIFY_JSON =  metadata + "/properties/classify.json"

export const MORE_JSON = metadata + "/header/more.json"

export const ZHUANLAN_JSON = metadata + "/header/zhuanlan.json"

export const OPENSOURCE_JSON = metadata + "/header/opensource.json"

export const APP_STORE_JSON = metadata + "/header/appstore.json"

export const PLAY_DATASET_JSON =  metadata + "/modules/play/dataset.json"

export const ALL_PROPERTIES_DSL = dsl("all_properties", "", `
    id
    createdAt
    updatedAt
    classify
    name
    description
    enabled
    logo
    tag
    payload
`
)
