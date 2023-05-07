//
//  ContentView.swift
//  iosApp
//
//  Created by Qamar Safadi on 07/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import common

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}

