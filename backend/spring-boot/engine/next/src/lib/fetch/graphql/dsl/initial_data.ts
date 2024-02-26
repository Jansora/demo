import {dsl} from "@/lib/fetch/graphql/dsl/utils";
import {
    APP_STORE_JSON,
    MORE_JSON,
    OPENSOURCE_JSON,
    PROPERTIES_CLASSIFY_JSON,
    ZHUANLAN_JSON
} from "@/lib/fetch/graphql/dsl/properties";

export const INITIAL_DATA_DSL = dsl("initial_data", `(properties: [\"${MORE_JSON}\", \"${PROPERTIES_CLASSIFY_JSON}\", \"${ZHUANLAN_JSON}\", \"${OPENSOURCE_JSON}\", \"${APP_STORE_JSON}\"])`, `
    user {
        id
        createdAt
        updatedAt
        username
        alias
        description
        role
        avatar
        homepage
        email
    }
    properties {
        id
        name
        description
        payload
        classify
    }
    `
)