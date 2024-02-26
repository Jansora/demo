import {dsl} from "@/lib/fetch/graphql/dsl/utils";

export const CURRENT_USER_DSL = dsl("current_user", "", `
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
    `
)
