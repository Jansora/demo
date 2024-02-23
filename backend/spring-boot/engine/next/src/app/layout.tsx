import React from "react";
import RootLayout from "@jansora/ui/esm/components/enhanced/layout/RootLayout";
import Header from "@jansora/ui/esm/components/enhanced/layout/header";
import HeaderLeft from "@/components/header/HeaderLeft";
import GlobalStoreProvider from "@/lib/store/global";

import PaddedFullPageLayout from "@jansora/ui/esm/components/enhanced/layout/PaddedFullPageLayout";
import HeaderRight from "@/components/header/HeaderRight";
import LoadIconFont from "@/components/layout/LoadIconFont";
import {Toaster} from "sonner";


export default function Layout({children}) {

    const data = initialData();

    return (
        <RootLayout>
            <GlobalStoreProvider initialData={data}>
            <Header
                left={<HeaderLeft />}
                right={<HeaderRight />}
            />
                <PaddedFullPageLayout>

                    {children}

                </PaddedFullPageLayout>
            </GlobalStoreProvider>

            <Toaster richColors />
            <LoadIconFont />
        </RootLayout>
    )
}

const initialData = () => {

    // const config = loadConfig();

    const data= {

    }


    return data
}

