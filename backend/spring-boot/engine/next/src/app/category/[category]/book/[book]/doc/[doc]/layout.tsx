import {metadataCache} from "@/lib/config/initConfig";
import {metadata} from "@/lib/head";
import {Book, Category} from "@/lib/declares/config";

export async function generateMetadata({ params }) {
    const {category, book, doc} = params;

    const categoryFound: Category = metadataCache.categoryCache[category];
    const bookFound: Book = metadataCache.bookCache[book];
    const docFound: Book = metadataCache.docCache[doc];
    const a = metadataCache
    if (!categoryFound || !bookFound || !docFound) {
        console.log("doc 404", categoryFound, bookFound, docFound)
        // notFound()
        return metadata({})
    }


    return metadata({
        title: `${docFound.name} | ${bookFound.name} | ${categoryFound.name}`,
        description: `${docFound.name} | ${bookFound.name} | ${categoryFound.name}`,
    })

}

export default async function Layout({children}) {

    return (
        <>
            {children}
        </>
    )
}
