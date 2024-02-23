import React from "react";
import {Book, BookData, Category} from "@/lib/declares/config";
import {loadBookData, metadataCache} from "@/lib/config/initConfig";
import {notFound} from "next/navigation";
import {metadata} from "@/lib/head";
import RenderDoc from "@/app/category/[category]/book/[book]/doc/[doc]/RenderDoc";
import {isDoc, isHtml, isPdf, STATIC} from "@/lib/util/utils";
import RenderHtml from "@/app/category/[category]/book/[book]/doc/[doc]/RenderHtml";
import RenderPdf from "@/app/category/[category]/book/[book]/doc/[doc]/RenderPdf";
import path from "path";

/**
 * Critical: prevents "TypeError: url.replace is not a function" error
 */
// const RenderPdf = dynamic(() => import('./RenderPdf.js'), {
//     ssr: false,
// });


export async function generateMetadata({ params }) {
    const {category, book, doc} = params;

    const categoryFound: Category = metadataCache.categoryCache[category];
    const bookFound: Book = metadataCache.bookCache[book];
    const docFound: Book = metadataCache.docCache[doc];
    const a = metadataCache
    if (!categoryFound || !bookFound || !docFound) {
        notFound()
    }

    return metadata({
        title: `${docFound.name} | ${bookFound.name} | ${categoryFound.name}`,
        description: `${docFound.name} | ${bookFound.name} | ${categoryFound.name}`,
    })

}

export default function Page({params}) {

    const { category, book, doc } = params;

    const docFound: BookData = loadBookData(book, doc);
    const bookFound: Book = metadataCache.bookCache[book];

    if (!docFound) {
        return <></>
    }

    // const { name, path } = docFound;
    const resourcePath = `/category/${category}/book/${book}/doc/${STATIC}/${docFound.path.replace(bookFound.path, "")}`
    const fileName = path.basename(docFound.path);
    // console.log("isDoc", docFound)
    return (
        <>
            {
                isDoc(docFound.suffix) &&  <RenderDoc resourcePath={resourcePath} params={params} data={docFound}/>
            }
            {
                isHtml(docFound.suffix) && <RenderHtml resourcePath={resourcePath} params={params} data={docFound}/>
            }
            {
                isPdf(docFound.suffix) && <RenderPdf resourcePath={resourcePath} params={params} data={docFound}/>
            }
        </>
    )
}



