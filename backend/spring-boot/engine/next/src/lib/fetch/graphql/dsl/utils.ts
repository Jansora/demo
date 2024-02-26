import {SearchRequest} from "@/lib/declares/declares";

export const DSLAlias = "__data"


export const module_classify_DSL = (module, { keywords, classify, tag, pageSize, pageNum, orderBy, sort}: SearchRequest) => dsl(
    `module_classify`,
    `(req: {
        name    : "${keywords}", 
        module    : "${module}", 
        keywords: "${keywords}", 
        classify: "${classify}", 
        tag     : "${tag}", 
        sort    : "${sort}", 
        orderBy : "${orderBy}", 
        pageNum : ${pageNum}, 
        pageSize: ${pageSize}
    })`, `pageNum,
    pageSize,
    total,
    data {
        id
        createdAt
        updatedAt
        name
        description
        enabled
        logo
        tag
        author {
            id
            alias
            description
            avatar
            homepage
        }
    }`
)

export const module_search_DSL = (module, { keywords, classify, tag, pageSize, pageNum, orderBy, sort}: SearchRequest) => dsl(
    `module_search`,
        `(req: {
        name    : "${keywords}", 
        module    : "${module}", 
        keywords: "${keywords}", 
        classify: "${classify}", 
        tag     : "${tag}", 
        sort    : "${sort}", 
        orderBy : "${orderBy}", 
        pageNum : ${pageNum}, 
        pageSize: ${pageSize}
    })`, `pageNum,
    pageSize,
    total,
    data {
        id
        name
        nameHighlight
        payloadHighlight
    }`
)

export const module_init_DSL = (module) => dsl(
    `module_initial_data`,
    `( module: "${module}" )`, `
    
   
    logos {
      key
     value
   }
    classifies {
      key
     value
   }
    classifyCounts {
      key
     value
   }
    tags {
      key
      value
   }
    
 
    `
)


export const module_init_next_page_DSL = (module, { keywords, classify, tag, pageSize, pageNum, orderBy, sort}: SearchRequest) => dsl(
    `module_initial_data`,
    `(req: {
        name    : "${keywords}", 
        module    : "${module}", 
        keywords: "${keywords}", 
        classify: "${classify}", 
        tag     : "${tag}", 
        sort    : "${sort}", 
        orderBy : "${orderBy}", 
        pageNum : ${pageNum}, 
        pageSize: ${pageSize}
    })`, `
    
    data {
        ${module_search_response_field}
    }
    `
)




const module_search_response_field = `
    
        pageNum
        pageSize
        total
        data {
            id
            createdAt
            updatedAt
            name
            description
            enabled
            logo
            tag
            author {
                id
                alias
                description
                avatar
                homepage
            }
        }
    
`

export const hashDSL = (module, hash: string, accept: string) => dsl(
    `${module}`, `(hash: "${hash}")`, `${accept}`
)

export const oneDSL = (module, id: string, accept: string) => dsl(
    `${module}`, `(id: ${id})`, `${accept}`
)


export const dsl = (query: string, args: string, accept: string) => {
    const _dsl = `
query {
  ${DSLAlias}: ${query} ${args}
    {    
    ${accept} 
  }
}
`
    // console.log("Query DSL: ", _dsl)
    return _dsl;
}