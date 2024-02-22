import {metadataCache} from "@/lib/config/initConfig";
import {metadata} from "@/lib/head";
import {Category} from "@/lib/declares/config";
import SideContainer from "@jansora/ui/esm/components/enhanced/layout/side/SideContainer";
import SideNav from "@jansora/ui/esm/components/enhanced/layout/side/SideNav";
import List from "@jansora/ui/esm/components/enhanced/layout/list/List";
import SideContent from "@jansora/ui/esm/components/enhanced/layout/side/SideContent";
import React from "react";
import {ListItemProps} from "@jansora/ui/esm/lib/declares";

export async function generateMetadata({ params }) {
    const {category} = params;


    const found: Category = metadataCache.categoryCache[category];

    if (!found) {
        console.log("category 404", found)

        return metadata({})
    }

    return metadata({
        title: found.name,
        description: found.name,
    })

}

export default async function Layout({children, params}) {
    const {category} = params;
    const found: Category = metadataCache.categoryCache[category];

    const items: ListItemProps[] = found ? found.books.map(book => {
        return {
            title: book.name,
            href: `/category/${category}/book/${book.key}`,
        }
    }) : [];



    return (
        <>
            <SideContainer>
                <SideNav >
                    <List className="pt-2" items={items} loading={false}/>
                </SideNav>
                <SideContent className={"w-full"}>
                    {children}
                </SideContent>
            </SideContainer>
        </>
    )
}
